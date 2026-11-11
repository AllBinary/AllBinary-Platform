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

J2ME in 2001, J2SE in 2003, Android in 2007, web via GWT in 2011, Native via Avian a toy VM in 2021, Kotlin in 2025, and ArkTs in 2026.  In 2021 I added GDevelop JSON support so I can have a no code path to creating apps and games with my AllBinary Platform.

### Future

In 2027 I will release a scene editor and continue to improve the polyglot support.

## Desktop target types

"Desktop" means the code runs on Windows, Linux, and macOS. *How* it runs depends on the language:

| Tag | Meaning | Examples |
|-----|---------|----------|
| **Native** | Compiled ahead of time to a machine-code executable | C++, Rust, Go, Zig, Ada, Haskell (GHC), OCaml (ocamlopt), Object Pascal |
| **Managed VM** | Compiled to bytecode and run on a language VM | C# (.NET CLR; NativeAOT possible), Erlang/Elixir (BEAM) |
| **JVM** | Runs on the Java VM (the J2SE target) | Java, Kotlin, Scala, Groovy |
| **Interpreted** | Run as source by the language interpreter | Python, Perl, Ruby, Lua, Tcl |
| **Web** | Browser/WebView, HTML5, or WASM | JavaScript, TypeScript |
| **Mobile** | Android, iOS, HarmonyOS | Kotlin, Swift, ArkTS |

## Current build state

### Tier 0 – Games, Thickclients, and Services run
public repo, private repo, and GD poms for server, thick clients, and J2ME client code builds and deploys:

| Language | Targets | Notes |
|----------|---------|-------|
| Java | J2SE, J2ME (Legacy games), Android, Native via Avian, Web with Javascript with PlayN 1 and 2 with 100% GWT | Produced from the TypeScript output by `tsc` (about 1% GWT). Allows dynamic preview of games. | Graal works as of 2024, but it not deployed

### Tier 1 – Games run
The public repo, private repo, and GD (GDevelop-generated game) root pom all build. J2ME-style
client code builds, and most AllBinary legacy and GD games run.

| Language | Targets | Notes |
|----------|---------|-------|
| JavaScript | Web | Produced from the TypeScript output by `tsc` (about 1% GWT). Allows dynamic preview of games. |
| Kotlin | JVM, Android (KMP: iOS, Web/WASM, Native) | |

### Tier 2 – Legacy game runs
The public and private repo root pom build. J2ME-style client code builds, and at least one legacy game runs.

| Language | Targets | Notes |
|----------|---------|-------|
| TypeScript | Web, Mobile (HarmonyOS via ArkTS) | Also the source for the JavaScript output (via `tsc`). |

### Tier 3 – Public root pom build (with 5 private libraries)

| Language | Desktop | Notes |
|----------|---------|-------|
| C++ | Native | |
| C# | Managed VM | .NET CLR. NativeAOT could make a true native build later. |
| Erlang | Managed VM | BEAM VM, built through `escript`. |

### Tier 4 – AllBinaryBase builds (with 5 private library)

| Language | Desktop | Notes |
|----------|---------|-------|
| Ada | Native | GNAT |
| Go | Native | |
| Haskell | Native | GHC |
| OCaml | Native | ocamlopt (bytecode is also possible) |
| Object Pascal | Native | Free Pascal |
| Rust | Native | |
| Zig | Native | |
| Python | Interpreted | |
| Perl | Interpreted | |
| Ruby | Interpreted | |
| Lua | Interpreted | |

### Planned – Initial commit not yet added or in progress

| Language | Desktop / Target | Notes |
|----------|------------------|-------|
| C99 | Native | |
| Objective-C | Native | macOS-first. Also an iOS path. |
| Swift | Mobile (iOS), Native | Also compiles natively on macOS, Linux, and Windows. |
| Dart | Native | `dart compile exe`. Also Flutter mobile. |
| FORTRAN | Native | |
| COBOL | Native | GnuCOBOL compiles through C. |
| ASM | Native | Platform/ISA specific |
| Elixir | Managed VM | BEAM. Shares a runtime with Erlang. |
| Scala | JVM | J2SE (Scala Native is a possible later step) |
| Groovy | JVM | J2SE |
| Tcl | Interpreted | |
| ActionScript | Flash/AIR | Matches the Flash/ActionScript target in the diagram. |
| Haxe | Native (via the hxcpp C++ target) | Haxe can also target JS, JVM, and HashLink. |
