## AllBinary Platform – Polyglot Support

The AllBinary Platform is written once in Java (J2ME/MIDP-style client code plus the
platform libraries). That Java source is the single source of truth: it is converted
(Java → XML → XSL) into equivalent code for other languages, so the same games and
libraries can reach targets that Java alone cannot.

In the [platform diagram](AllBinary_Platform_Diagram.png) this is the **Polyglot (Other
Languages)** box. It sits beside the dedicated **Kotlin/KMP** and **TypeScript/JavaScript**
paths, and all three feed the same set of targets (Android, iOS, Browser/HTML5, WASM,
HarmonyOS, J2SE, and Native Win/Linux/Mac).

The language specific repos hold the generated code, so developers can use it without 
running the generator themselves.

### Polyglot History

J2ME in 2001, J2SE in 2003, Android in 2007, web via GWT in 2011, Native via Avian a toy VM in 2021, Kotlin in 2025, and ArkTs in 2026.  In 2021 GDevelop JSON support was added for a no code path to creating apps and games with the AllBinary Platform.

### Future

In 2027 a scene editor will be released and continued work improve the polyglot support.

## Terms

Each language in the tables below is described two ways:

- **Language type** – *how* the generated code runs.
- **Targets** – *where* it runs.

### Language types

| Language type | Meaning | Examples |
|---------------|---------|----------|
| **Native** | Compiled ahead of time to a machine-code executable | C++, Rust, Go, Zig, Ada, Haskell (GHC), OCaml (ocamlopt), Object Pascal |
| **Managed VM** | Compiled to bytecode and run on a non-Java language VM | C# (.NET CLR; NativeAOT possible), Erlang/Elixir (BEAM) |
| **JVM** | Compiled to Java bytecode and run on a Java VM | Java, Kotlin, Scala, Groovy |
| **Interpreted** | Run as source by the language interpreter | Python, Perl, Ruby, Lua, Tcl |
| **JS engine** | Run by a browser or WebView JavaScript engine (or compiled to WASM) | JavaScript, TypeScript, ArkTS |

### Targets

| Target | Meaning |
|--------|---------|
| **Desktop** | Windows, Linux, and macOS. Desktop native focused languages use SWT and OpenGL only. |
| **Web** | Browser/WebView, HTML5, or WASM |
| **Mobile** | Android, iOS, HarmonyOS |
| **J2ME** | Legacy J2ME/MIDP phones (Java only) |
| **Server** | Services and server code (Java only today) |

## Current build state

### Tier 0 – Games, Thickclients, and Services run
public repo, private repo, and GD poms for server, thick clients, and J2ME client code builds and deploys:

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| Java | JVM, Native (via Avian), JS engine (via GWT) | Desktop (J2SE), Server, J2ME (legacy games), Mobile (Android), Web (PlayN 1 and 2 with 100% GWT) | GraalVM native works as of 2024, but is not deployed. |

### Tier 1 – Games run
The public repo, private repo, and GDevelop JSON conversion all build. J2ME-style client code builds, and most AllBinary legacy and supported GDevelop JSON games run.

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| JavaScript | JS engine | Web | Produced from the TypeScript output by `tsc` (about 1% GWT). Allows dynamic preview of games. |
| Kotlin | JVM (KMP: Native, JS engine) | Desktop, Mobile (Android; iOS via KMP), Web/WASM (via KMP) | |

### Tier 2 – AllBinary legacy game runs
The public and private repo root pom build. J2ME-style client code builds, and at least one legacy game runs.

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| TypeScript | JS engine | Web, Mobile (HarmonyOS via ArkTS) | Also the source for the JavaScript output (via `tsc`). |

### Tier 3 – Public root pom build (with 5 private libraries)

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| C++ | Native | Desktop | |
| C# | Managed VM | Desktop | .NET CLR. NativeAOT could make a true native build later. |
| Erlang | Managed VM | Desktop | BEAM VM, built through `escript`. |

### Tier 4 – AllBinaryBase builds (with 5 private library)

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| Ada | Native | Desktop | GNAT |
| Go | Native | Desktop | |
| Haskell | Native | Desktop | GHC |
| OCaml | Native | Desktop | ocamlopt (bytecode is also possible) |
| Object Pascal | Native | Desktop | Free Pascal |
| Rust | Native | Desktop | |
| Zig | Native | Desktop | |
| Python | Interpreted | Desktop | |
| Perl | Interpreted | Desktop | |
| Ruby | Interpreted | Desktop | |
| Lua | Interpreted | Desktop | |

### Planned – Initial commit not yet added or in progress

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| C99 | Native | Desktop | |
| Objective-C | Native | Desktop (macOS first), Mobile (iOS) | |
| Swift | Native | Mobile (iOS), Desktop | Compiles natively on macOS, Linux, and Windows. |
| Dart | Native | Desktop, Mobile (via Flutter) | `dart compile exe`. |
| FORTRAN | Native | Desktop | |
| COBOL | Native | Desktop | GnuCOBOL compiles through C. |
| ASM | Native | Desktop | Platform/ISA specific |
| Elixir | Managed VM | Desktop | BEAM. Shares a runtime with Erlang. |
| Scala | JVM | Desktop (J2SE) | Scala Native is a possible later step. |
| Groovy | JVM | Desktop (J2SE) | |
| Tcl | Interpreted | Desktop | |
| ActionScript | Managed VM (Flash/AIR) | Web (Flash), Desktop and Mobile (AIR) | Matches the Flash/ActionScript target in the diagram. |
| Haxe | Native (via the hxcpp C++ target) | Desktop | Haxe can also target JS, JVM, and HashLink. |
