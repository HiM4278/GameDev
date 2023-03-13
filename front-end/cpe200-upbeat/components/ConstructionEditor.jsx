import React, { useEffect, useRef } from "react";
import Editor, { loader } from "@monaco-editor/react";

export default function ConstructionEditor() {
  const effectRan = useRef(false);
  var keywords = [
    "collect",
    "done",
    "else",
    "if",
    "invest",
    "move",
    "nearby",
    "opponent",
    "relocate",
    "shoot",
    "then",
    "while",
  ];

  let operators = ["=", "+", "-", "*", "/", "^", "%"];

  useEffect(() => {
    if (effectRan.current === false) {
      loader.init().then((monaco) => {
        monaco.languages.register({ id: "upbeat" });
        monaco.languages.setLanguageConfiguration("upbeat", {
          comments: [{ lineComment: /(^#.*$)/ }],
        });
        monaco.languages.setMonarchTokensProvider("upbeat", {
          keywords,

          typeKeywords: [
            "down",
            "downleft",
            "downright",
            "up",
            "upleft",
            "upright",
          ],

          operators,

          // we include these common regular expressions
          symbols: /[=><!~?:&|+\-*\/\^%]+/,

          // C# style strings
          escapes:
            /\\(?:[abfnrtv\\"']|x[0-9A-Fa-f]{1,4}|u[0-9A-Fa-f]{4}|U[0-9A-Fa-f]{8})/,

          // The main tokenizer for our languages
          tokenizer: {
            root: [
              // identifiers and keywords
              [
                /[a-z_$][\w$]*/,
                {
                  cases: {
                    "@typeKeywords": "string",
                    "@keywords": "keyword",
                    "@default": "identifier",
                  },
                },
              ],
              [/[A-Z][\w\$]*/, "type.identifier"], // to show class names nicely

              // whitespace
              { include: "@whitespace" },

              // delimiters and operators
              [/[{}()\[\]]/, "@brackets"],
              [/[<>](?!@symbols)/, "@brackets"],
              [
                /@symbols/,
                { cases: { "@operators": "operator", "@default": "" } },
              ],

              // numbers
              [/\d*\.\d+([eE][\-+]?\d+)?/, "number.float"],
              [/0[xX][0-9a-fA-F]+/, "number.hex"],
              [/\d+/, "number"],

              // delimiter: after number because of .\d floats
              [/[;,.]/, "delimiter"],
            ],

            comment: [[/(^#.*$)/, "comment"]],

            string: [
              [/[^\\"]+/, "string"],
              [/@escapes/, "string.escape"],
              [/\\./, "string.escape.invalid"],
              [/"/, { token: "string.quote", bracket: "@close", next: "@pop" }],
            ],

            whitespace: [
              [/[ \t\r\n]+/, "white"],
              [/(^#.*$)/, "comment"],
            ],
          },
        });
        monaco.languages.registerCompletionItemProvider("upbeat", {
          provideCompletionItems: (model, position) => {
            const suggestions = [
              ...keywords.map((k) => {
                return {
                  label: k,
                  kind: monaco.languages.CompletionItemKind.Keyword,
                  insertText: k,
                };
              }),
            ];
            return { suggestions };
          },
        });
      });
    }
    return () => {
      effectRan.current = true;
    };
  }, []);

  return (
    <div className="plan-sidebar">
      <div className="editor-header">
        My Construction Plan
        <div className="editor-btn-run">
          <div className="editor.timer" style={{ marginRight: "15px" }}>
            30:00s
          </div>
          <img src="Run_Button.png" width={50}></img>
        </div>
      </div>
      <div className="editor-container">
        <Editor
          height="100vh"
          width={`100%`}
          language={"upbeat"}
          value={""}
          s
          theme={"tomorrow-night"}
          defaultValue="# Construct here"
        />
      </div>
    </div>
  );
}
