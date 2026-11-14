## AllBinary Platform – Polyglot Support

The AllBinary Platform is written once in Java (J2ME/MIDP-style client code plus the
platform libraries). That Java source is the single source of truth: it is converted
(Java → XML → XSL) into equivalent code for other languages, so the same games and
libraries can reach targets that Java alone cannot.

In the [platform diagram](AllBinary_Platform_Diagram.png) this is the **Polyglot (Other
Languages)** box. It sits beside the dedicated **Kotlin/KMP** and **TypeScript/JavaScript**
paths, and all three feed the same set of targets (Android, iOS, Browser/HTML5, WASM,
HarmonyOS, J2SE, and Desktop: Windows, Linux, and macOS).

The language-specific repos hold the generated code, so developers can use it without
running the generator themselves. Each language name in the tables below links to its
repo when one exists.

### Polyglot History

| Year | Milestone |
|------|-----------|
| 2001 | J2ME |
| 2003 | J2SE |
| 2007 | Android |
| 2011 | Web via 100% GWT with PlayN 1 |
| 2021 | Native via Avian, a small embedded Java VM |
| 2021 | GDevelop JSON support, a no-code path to creating apps and games with the AllBinary Platform |
| 2025 | Kotlin |
| 2026 | ArkTS, Web via 100% GWT with PlayN 2, Web via 1% GWT with PlayN 2 |

### Future

| Year | Milestone |
|------|-----------|
| 2027 | AllBinary Scene Editor, Many Polyglot Tier upgrades |

### Terms

Each language in the tables below is described two ways:

- **Language type** – *how* the generated code runs.
- **Targets** – *where* it runs.

### Language types

| Language type | Meaning | Examples |
|---------------|---------|----------|
| **Native** | Compiled ahead of time to a machine-code executable | C++, Rust, Go, Zig, Ada, Haskell (GHC), OCaml (ocamlopt), Object Pascal |
| **Managed VM** | Compiled to bytecode and run on a non-Java language VM | C# (.NET CLR; NativeAOT possible), Erlang/Elixir (BEAM), ArkTS (Ark runtime) |
| **JVM** | Compiled to Java bytecode and run on a Java VM | Java, Kotlin, Scala, Groovy |
| **JIT** | Compiled to machine code at run time, with no separate bytecode VM step | Julia (LLVM) |
| **Interpreted** | Run from source by the language interpreter | Python, Perl, Ruby, Lua, Tcl |
| **JS engine** | Run by a browser or WebView JavaScript engine (or compiled to WASM) | JavaScript, TypeScript |

### Targets

| Target | Meaning |
|--------|---------|
| **Desktop** | Windows, Linux, and macOS. Languages that run natively on Desktop use only SWT-style windowing and OpenGL. |
| **Web** | Browser/WebView, HTML5, or WASM |
| **Mobile** | Android, iOS, HarmonyOS |
| **J2ME** | Legacy J2ME/MIDP phones (Java only) |
| **Server** | Services and server code (Java only today) |

## Current build state

### Tier 0 – Games (https://allbinary.itch.io), thick clients, and services run

The public repo, private repo, and GDevelop poms for server, thick client, and J2ME client code all build and deploy.

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| [Java](https://github.com/AllBinary/AllBinary-Platform) | JVM, Native executable (embedded Avian VM), JS engine (via GWT) | Desktop (J2SE), Server, J2ME (legacy games), Mobile (Android), Web (PlayN 1 and 2, all compiled by GWT) | For Web, GWT compiles the whole Java app to JavaScript. GraalVM native works as of 2024, but is not deployed. |

### Tier 1 – Games (https://allbinary.itch.io) run

The public repo, private repo, and GDevelop JSON conversion all build. J2ME-style client code builds, and AllBinary legacy and supported GDevelop JSON games run.

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| [JavaScript](https://github.com/AllBinary/AllBinary-Platform-Javascript) | JS engine | Web | Produced from the TypeScript output by `tsc`; only about 1% of the code still comes from GWT. Allows dynamic preview of games. |
| [Kotlin](https://github.com/AllBinary/AllBinary-Platform-KMP) | JVM (KMP: Native, JS engine) | Desktop, Mobile (Android; iOS via KMP), Web/WASM (via KMP) | |

### Tier 2 – AllBinary legacy game(s) (https://allbinary.itch.io) run

The public and private repo root poms build. J2ME-style client code builds, and at least one legacy game runs.

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| [TypeScript](https://github.com/AllBinary/AllBinary-Platform-TypeScript) | JS engine (ArkTS: Managed VM) | Web, Mobile (HarmonyOS via ArkTS) | Also the source for the JavaScript output (via `tsc`). ArkTS compiles to Ark bytecode, which runs on the Ark runtime. |

### Tier 3 – Public root pom build (with 5 private libraries)

The public repo root pom builds, together with the 5 private libraries it needs.

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| [C++](https://github.com/AllBinary/AllBinary-Platform-C-CPP) | Native | Desktop | |
| [C#](https://github.com/AllBinary/AllBinary-Platform-CSharp) | Managed VM | Desktop | .NET CLR. NativeAOT could make a true native build later. |
| [Erlang](https://github.com/AllBinary/AllBinary-Platform-Erlang) | Managed VM | Desktop | BEAM VM, built through `escript`. |
| [Go](https://github.com/AllBinary/AllBinary-Platform-Go) | Native | Desktop | |
| [Lua](https://github.com/AllBinary/AllBinary-Platform-Lua) | Interpreted | Desktop | |
| [Python](https://github.com/AllBinary/AllBinary-Platform-Python) | Interpreted | Desktop | |

### Tier 4 – AllBinaryBase builds (with 5 private libraries)

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| [Ada](https://github.com/AllBinary/AllBinary-Platform-Ada) | Native | Desktop | GNAT |
| [Haskell](https://github.com/AllBinary/AllBinary-Platform-Haskell) | Native | Desktop | GHC |
| [OCaml](https://github.com/AllBinary/AllBinary-Platform-Ocaml) | Native | Desktop | ocamlopt (bytecode is also possible) |
| [Object Pascal](https://github.com/AllBinary/AllBinary-Platform-ObjectPascal) | Native | Desktop | Free Pascal |
| [Rust](https://github.com/AllBinary/AllBinary-Platform-Rust) | Native | Desktop | |
| [Zig](https://github.com/AllBinary/AllBinary-Platform-Zig) | Native | Desktop | |
| [Perl](https://github.com/AllBinary/AllBinary-Platform-Perl) | Interpreted | Desktop | |
| [Ruby](https://github.com/AllBinary/AllBinary-Platform-Ruby) | Interpreted | Desktop | |

### Planned – Initial commit not yet added or in progress

Grouped by language type.

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| [C99](https://github.com/AllBinary/AllBinary-Platform-C-CPP) | Native | Desktop | |
| [Objective-C](https://github.com/AllBinary/AllBinary-Platform-Objective-C) | Native | Desktop (macOS first), Mobile (iOS) | |
| [Swift](https://github.com/AllBinary/AllBinary-Platform-Swift) | Native | Mobile (iOS), Desktop | Compiles natively on macOS, Linux, and Windows. |
| [Dart](https://github.com/AllBinary/AllBinary-Platform-Dart) | Native | Desktop, Mobile (via Flutter), Web (JS/WASM) | `dart compile exe` for Desktop; `dart compile js` or `wasm` for Web. |
| [FORTRAN](https://github.com/AllBinary/AllBinary-Platform-FORTRAN) | Native | Desktop | |
| [COBOL](https://github.com/AllBinary/AllBinary-Platform-COBOL) | Native | Desktop | GnuCOBOL compiles through C. |
| [ASM](https://github.com/AllBinary/AllBinary-Platform-ASM) | Native | Desktop | Platform/ISA specific |
| [Haxe](https://github.com/AllBinary/AllBinary-Platform-Haxe) | Native (via the hxcpp C++ target) | Desktop | Haxe can also target JS, JVM, and HashLink. |
| [Nim](https://github.com/AllBinary/AllBinary-Platform-Nim) | Native (compiles through C) | Desktop | Raylib/SDL2 bindings. Deterministic memory management (ARC/ORC). Smaller community than C++/C#. |
| [Vala](https://github.com/AllBinary/AllBinary-Platform-Vala) | Native (compiles through C) | Desktop (Linux first) | Built on GObject; mostly used with GNOME. |
| [Lisp](https://github.com/AllBinary/AllBinary-Platform-Lisp-with-native-call-support) | Native (e.g. SBCL) | Desktop | Code can be redefined while the program runs (REPL). Garbage collected; smaller library ecosystem. |
| [Racket](https://github.com/AllBinary/AllBinary-Platform-Racket) | Native (Chez Scheme backend) | Desktop | Includes 2D graphics libraries. Standalone apps via `raco exe` and `raco distribute`. |
| [Scheme](https://github.com/AllBinary/AllBinary-Platform-Scheme) | Native (via Chicken/Gambit C) | Desktop | Many separate implementations; Chicken and Gambit compile through C. |
| [Oberon+](https://github.com/AllBinary/AllBinary-Platform-OberonPlus) | Native | Desktop | Few game libraries. |
| [ActionScript](https://github.com/AllBinary/AllBinary-Platform-ActionScript) | Managed VM (AIR) | Desktop and Mobile (AIR), Web (Ruffle) | Flash Player reached end of life in 2020; AIR is now maintained by Harman. Matches the Flash/ActionScript target in the diagram. |
| [Elixir](https://github.com/AllBinary/AllBinary-Platform-Elixir) | Managed VM | Desktop | BEAM. Shares a runtime with Erlang. |
| [Scala](https://github.com/AllBinary/AllBinary-Platform-Scala) | JVM | Desktop (J2SE) | Scala Native is a possible later step. |
| [Groovy](https://github.com/AllBinary/AllBinary-Platform-Groovy) | JVM | Desktop (J2SE) | |
| [Julia](https://github.com/AllBinary/AllBinary-Platform-Julia) | JIT (LLVM) | Desktop | Designed for numerical computing. Code compiles on first call, so startup is slower; standalone executables via PackageCompiler or `juliac`. |
| [Tcl](https://github.com/AllBinary/AllBinary-Platform-Tcl) | Interpreted | Desktop | |
| [Wren](https://github.com/AllBinary/AllBinary-Platform-Wren) | Interpreted (embedded) | Desktop (inside a host engine) | Designed for embedding in game engines. Requires a host C/C++ engine. |
| [Janet](https://github.com/AllBinary/AllBinary-Platform-Janet) | Interpreted (embedded) | Desktop | Embeddable, with a simple C FFI. |
| [Grain](https://github.com/AllBinary/AllBinary-Platform-Grain) | JS engine (WASM) | Web | Compiles to WebAssembly. Young language and ecosystem. |
| [Elm](https://github.com/AllBinary/AllBinary-Platform-Elm) | JS engine | Web | Pure functional; game state is updated through messages (The Elm Architecture). |
