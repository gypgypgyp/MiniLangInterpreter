# MiniLangInterpreter

This project is a Java-based interpreter for a simple programming language, MiniLang. It provides the following key functionalities:

Expressions and Abstract Syntax Tree (AST): Creates representations for expressions (such as integers, booleans, function calls, and operators) using an AST structure.

Evaluator: Implements a visitor-based evaluator to interpret expressions and compute their values, managing environments to handle function definitions and variables.

Primitives: Defines basic arithmetic, relational, and logical operations, adding them to a PrimitiveTable for use in the interpreter.

Format Method: Provides a format method for each AST class to produce user-readable output.

Testing and Code Coverage: Ensures at least 70% code coverage for critical packages using JaCoCo.