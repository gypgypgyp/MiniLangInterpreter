package cs5004.parser;

import cs5004.ast.*;
import cs5004.parser.reader.SexpReader;
import cs5004.parser.sexp.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Parser {
  private final SexpReader reader;

  private Parser(SexpReader reader) {
    this.reader = Objects.requireNonNull(reader);
  }

  private Parser(String sourceName, String sourceText) {
    this.reader =
      new SexpReader(sourceName, new java.io.StringReader(sourceText));
  }

  public static Program parseProgram(
    String sourceName,
    String sourceText
  ) {
    Parser p = new Parser(sourceName, sourceText);
    return p.parseProgram();
  }

  public static Expression parseExpression(
    String sourceName,
    String sourceText
  ) {
    return Parser.parseProgram(sourceName, sourceText).getExpression();
  }

  private Program parseProgram() {
    List<Definition> definitions = new ArrayList<>();

    Sexp sexp = reader.read();
    while (isDefinition(sexp)) {
      definitions.add(parseDefinition(sexp));
      sexp = reader.read();
    }
    Expression expr = parseExpression(sexp);
    Sexp trailingSexp = reader.read();
    if (!(trailingSexp instanceof Eof)) {
      throw new ParseError(
        trailingSexp.getPosn(),
        "unexpected input after expression"
      );
    }

    return new Program(definitions, expr);
  }

  private boolean isDefinition(Sexp sexp) {
    if (sexp instanceof SexpList list) {
      List<Sexp> items = list.getItems();
      return (
        !items.isEmpty() &&
        items.get(0) instanceof Symbol leadingSymbol
        && leadingSymbol.getName().equals("define")
      );
    } else {
      return false;
    }
  }

  private Definition parseDefinition(Sexp sexp) {
    List<Sexp> items = ((SexpList) sexp).getItems();
    if (items.size() != 3) {
      throw new ParseError(
        sexp.getPosn(),
        "definition must have form (define (<name> <arg> ...) <body>)"
      );
    }
    Pair<String, List<String>> header =
      parseDefinitionHeader(items.get(1));
    return new Definition(
      header.first(),
      header.second(),
      parseExpression(items.get(2))
    );
  }

  private Pair<String, List<String>> parseDefinitionHeader(Sexp src) {
    if (src instanceof SexpList list) {
      List<Sexp> items = list.getItems();
      if (items.isEmpty()) {
        throw new ParseError(
          list.getPosn(),
          "definition header must have at least one item (function name)"
        );
      }
      String defnName = expectSymbol(
        items.get(0),
        (Sexp s) -> new ParseError(
            s.getPosn(),
            "definition header must begin with function name; got "
              + s.unparse()
          )
      );

      List<String> argumentNames =
        items.stream()
          .skip(1)
          .map(s -> expectSymbol(
            s,
            (Sexp badSexp) -> new ParseError(
              badSexp.getPosn(),
              "definition header must contain argument names; got " +
                badSexp.unparse()
              )
            )
          ).toList();
      return new Pair<>(defnName, argumentNames);
    } else {
      throw new ParseError(
        src.getPosn(),
        "definition must start with a header"
      );
    }
  }

  private Expression parseExpression(Sexp sexp) {
    if (sexp instanceof IntLit intLit) {
      return new IntLiteral(intLit.getValue());
    } else if (sexp instanceof BoolLit boolLit) {
      return new BooleanLiteral(boolLit.getValue());
    } else if (sexp instanceof Symbol symbol) {
      return new VariableReference(symbol.getName());
    } else if (sexp instanceof SexpList list) {
      List<Sexp> items = list.getItems();
      if (items.isEmpty()) {
        throw new ParseError(sexp.getPosn(), "empty list");
      } else {
        String head = expectSymbol(
          items.get(0),
          (Sexp item) ->
            new ParseError(
              item.getPosn(),
              "head of list must be keyword or identifier; got " +
                item.unparse()
            )
        );
        switch (head) {
          case "and":
            return parseAndExpression(items);
          case "if":
            return parseIfExpression(items);
          case "let":
            return parseLetExpression(items);
          case "or":
            return parseOrExpression(items);
          default:
            return parseFunctionCall(items);
        }
      }
    } else {
      throw new ParseError(
        sexp.getPosn(),
        "unexpected input " + sexp.unparse()
      );
    }
  }

  private String expectSymbol(Sexp s, Function<Sexp, RuntimeException> mkExn) {
    if (s instanceof Symbol sym) {
      return sym.getName();
    } else {
      throw mkExn.apply(s);
    }
  }

  private AndExpression parseAndExpression(List<Sexp> items) {
    if (items.size() != 3) {
      throw new ParseError(
        items.get(0).getPosn(),
        "AND expression must have exactly 2 subexpressions"
      );
    }
    return new AndExpression(
      parseExpression(items.get(1)),
      parseExpression(items.get(2))
    );
  }

  private IfExpression parseIfExpression(List<Sexp> items) {
    if (items.size() != 4) {
      throw new ParseError(
        items.get(0).getPosn(),
        "if expression must have exactly 3 components: condition, then, else"
      );
    }

    return new IfExpression(
      parseExpression(items.get(1)),
      parseExpression(items.get(2)),
      parseExpression(items.get(3))
    );
  }

  private LetExpression parseLetExpression(List<Sexp> items) {
    if (items.size() != 4) {
      throw new ParseError(
        items.get(0).getPosn(),
        "'let' expression must have exactly 3 components: " +
          "variable name, initializer, body"
      );
    }
    String varName = expectSymbol(
      items.get(1),
      s -> new ParseError(
        s.getPosn(),
        "First component of let must be a variable name; got " +
          s.unparse()
      )
    );

    return new LetExpression(
      varName,
      parseExpression(items.get(2)),
      parseExpression(items.get(3))
    );
  }

  private OrExpression parseOrExpression(List<Sexp> items) {
    if (items.size() != 3) {
      throw new ParseError(
        items.get(0).getPosn(),
        "OR expression must have exactly 2 subexpressions"
      );
    } else {
      return new OrExpression(
        parseExpression(items.get(1)),
        parseExpression(items.get(2))
      );
    }
  }

  // assume that items is not empty
  private FunctionCall parseFunctionCall(List<Sexp> items) {
    String functionName = expectSymbol(
      items.get(0),
      s -> new ParseError(
        s.getPosn(),
        "first part of a function application must be a function name; got " +
          s.unparse()
      )
    );

    return new FunctionCall(
      functionName,
      items.stream()
        .skip(1)
        .map(this::parseExpression)
        .toList()
    );
  }
}