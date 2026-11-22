## AllBinary Platform – Polyglot Support

The AllBinary Platform is written once in Java (J2ME/MIDP-style client code plus the
platform libraries). That Java source is the single source of truth: it is converted
(Java → XML → XSL) into equivalent code for other languages, so the same games and
libraries can reach targets that Java alone cannot.

In the [platform diagram](AllBinary_Platform_Diagram.png) this is the **Polyglot (Other
Languages)** box. It sits beside the dedicated **Kotlin/KMP** and **TypeScript/JavaScript**
paths. All three are intended to feed the same set of targets (Android, iOS, Browser/HTML5,
WASM, HarmonyOS, J2SE, and Desktop: Windows, Linux, and macOS). The Polyglot languages
target Desktop only for now; see the tables below for what each language reaches now.

The language-specific repos hold the generated code, so developers can use it without
running the generator themselves. Language names link to information about the language. The adjacent repo link opens the AllBinary repository where available.

### Polyglot History

| Year | Milestone |
|------|-----------|
| 2001 | J2ME |
| 2003 | J2SE |
| 2007 | Android |
| 2011 | Web via 100% GWT with PlayN 1 |
| 2021 | Native via Avian (C++), a small embedded Java VM |
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
| **Interpreted** | Run from source by the language interpreter (most compile to an internal bytecode first) | Python, Perl, Ruby, Lua, Tcl |
| **JS engine** | Run by a browser or WebView engine, as JavaScript or as WebAssembly (WASM) | JavaScript, TypeScript |

### Targets

| Target | Meaning |
|--------|---------|
| **Desktop** | Windows, Linux, and macOS. Languages that run natively on Desktop use only SWT-style windowing and OpenGL. |
| **Web** | Browser/WebView, HTML5, or WASM |
| **Mobile** | Android, iOS, HarmonyOS |
| **J2ME** | Legacy J2ME/MIDP phones (Java only) |
| **Server** | Services and server code (Java only today) |

## Platform support tiers

### Tier 1a Thick Clients and Server/Services deployed - No immediate plans to change this

- J2SE for Windows, Mac, and Linux

### Tier 1b – Games (https://allbinary.itch.io) deployed

- Windows via Avian
- Android - including Ouya (special Scanline and game controller support), HarmonyOS pre 5, Legacy Android TV / Google TV - deployed on Google Play for the Logitech Revue, ChromeOS / Chromebooks - emulated via Android from Google Play
- Web via HTML5/JavaScript

### Tier 2 – Games run but not currently deployed

- J2ME/MIDP (currently limited to 2d legacy AllBinary games) - legacy games on various J2ME stores even a few on Blackberry's App Store
- Android Fire OS for Amazon Fire TV - legacy games were on Amazon App store at one point
- Android XR - needs polish
- Android TV / Google TV - needs polish and newer API usage
- Meta Quest / Horizon OS - needs polish but still just Android
- Google Play Games on PC - emulation works of and on depending on the Google Play Games on PC release
- Linux - games
- HarmonyOS 5+ via ArkTS
- macOS
- iOS, iPadOS, Apple Vision Pro / visionOS - still needs better windowed app behavior
- Windows - tested with GraalVM, but not deployed

### Tier 3 – Planned

- Web via WebAssembly (WASM)
- Samsung Tizen
- LG webOS
- Amazon Fire TV — Vega OS
- KaiOS
- Nintendo Switch
- Nintendo Switch 2
- PlayStation
- Xbox - DirectX 12 and Microsoft GDK (I have a unreleased game that uses DirectX already)
- Steam Deck / SteamOS - builds on existing Linux support; Steam-specific features could be added
- Android Auto and Android Automotive OS
- Apple TV / tvOS
- Roku OS - evaluate a BrightScript port for 2D games

## Current build state

### Tier 0 – Games (https://allbinary.itch.io), thick clients, and services run

The public repo, private repo, and GDevelop poms for server, thick client, and J2ME client code all build and deploy.

| Language/AllBinary Repo | Language type | Targets | Notes |
|-------------------------|---------------|---------|----------------|
| [Java](https://dev.java/) / [repo](https://github.com/AllBinary/AllBinary-Platform) | JVM (Android: ART/DEX), JVM embedded in a native executable (Avian VM), JS engine (via GWT) | Desktop (J2SE), Server, J2ME (legacy games), Mobile (Android), Web (PlayN 1 and 2, all compiled by GWT) | For Web, GWT compiles the whole Java app to JavaScript. GraalVM native works as of 2024, but is not deployed. |

### Tier 1 – Games (https://allbinary.itch.io) run

The public repo, private repo, and GDevelop JSON conversion all build. J2ME-style client code builds, and AllBinary legacy and supported GDevelop JSON games run.

| Language/AllBinary Repo | Language type | Targets | Notes |
|-------------------------|---------------|---------|----------------|
| [JavaScript](https://developer.mozilla.org/en-US/docs/Web/JavaScript) / [repo](https://github.com/AllBinary/AllBinary-Platform-Javascript) | JS engine | Web | Produced from the TypeScript output by `tsc`; only about 1% of the code still comes from GWT. Allows dynamic preview of games. |
| [Kotlin](https://kotlinlang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-KMP) | JVM (Android: ART/DEX; KMP: Native, JS engine via Kotlin/JS and Kotlin/Wasm) | Desktop, Mobile (Android; iOS via KMP), Web/WASM (via KMP) |  |

### Tier 2 – AllBinary legacy game(s) (https://allbinary.itch.io) run

The public and private repo root poms build. J2ME-style client code builds, and at least one legacy game runs.

| Language/AllBinary Repo | Language type | Targets | Notes |
|-------------------------|---------------|---------|----------------|
| [TypeScript](https://www.typescriptlang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-TypeScript) | JS engine (ArkTS: Managed VM) | Web, Mobile (HarmonyOS via ArkTS) | ArkTS compiles to Ark bytecode, which runs on the Ark runtime. Also the source for the JavaScript output (via `tsc`). |
| [C#](https://learn.microsoft.com/en-us/dotnet/csharp/) / [repo](https://github.com/AllBinary/AllBinary-Platform-CSharp) | Managed VM | Desktop | .NET CLR. | NativeAOT could make a true native build later. Runs MSVW via SWT |

### Tier 3 – Public root pom build (with 5 private libraries)

The public repo root pom builds, with 5 private libraries it needs.

| Language/AllBinary Repo | Language type | Targets | Notes | Other Progress |
|-------------------------|---------------|---------|----------------|----------------|
| [Ada](https://www.adaic.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Ada) | Native | Desktop | GNAT |  |
| [AngelScript](https://www.angelcode.com/angelscript/) / [repo](https://github.com/AllBinary/AllBinary-Platform-AngelScript) | Managed VM (embedded bytecode) | Desktop (inside a host engine) | Statically typed scripting with C++-like syntax, classes, and interfaces; embedded through a C++ host. | Converter, runtime, and poms in progress; checked with the ascheck host (AngelScript 2.39.0). |
| [AssemblyScript](https://www.assemblyscript.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-AssemblyScript) | JS engine (WASM) | Web | TypeScript-like language compiling to WebAssembly; browser integration requires host bindings. | Converter, runtime, and poms in progress; checked with asc 0.28 plus a transform that compiles every function (asc only compiles what the entry reaches). |
| [C23](https://www.open-std.org/jtc1/sc22/wg14/) / [repo](https://github.com/AllBinary/AllBinary-Platform-C-CPP) | Native | Desktop |  |  |
| [C++](https://isocpp.org/get-started) / [repo](https://github.com/AllBinary/AllBinary-Platform-C-CPP) | Native | Desktop |  |  |
| [Clojure](https://clojure.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Clojure) | JVM | Desktop (JVM) | Lisp on the JVM. | Converter, runtime, and poms in progress; compiled with clojure.main (Clojure 1.12.6) and jcheck, which declares every namespace first so classes can refer to each other in cycles. a converted class is a namespace with run time method dispatch, and java.* classes are the JVM classes through interop. ClojureScript (a separate compiler targeting JavaScript) is not used. |
| [D](https://dlang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-D) | Native (LLVM via LDC) | Desktop | Object-oriented systems language with garbage collection and C interoperability. | Converter, runtime, and poms in progress; checked with `ldc2 -o-` (LDC 1.43, D 2.113) over the runtime, the dependencies, and the project in one run. A Java class is a D class, an interface a D interface, and every file is a module that imports a generated module of all of them. |
| [Dart](https://dart.dev/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Dart) | Native, JS engine (dart2js / WASM) | Desktop, Mobile (via Flutter), Web (JS/WASM) | `dart compile exe` for Desktop; `dart compile js` or `wasm` for Web. |  |
| [Elixir](https://elixir-lang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Elixir) | Managed VM | Desktop | BEAM. Shares a runtime with Erlang. |  |
| [Erlang](https://www.erlang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Erlang) | Managed VM | Desktop | BEAM VM, built through `escript`. |  |
| [F#](https://learn.microsoft.com/en-us/dotnet/fsharp/what-is-fsharp) / [repo](https://github.com/AllBinary/AllBinary-Platform-FSharp) | Managed VM (.NET CLR) | Desktop | ML-family functional language with access to .NET libraries. | Converter, runtime, and poms done; the working root builds (Tier 3). Compiled with the .NET SDK F# compiler (`dotnet fsc.dll`, .NET 8 SDK) to one library per project. Candidate for MonoGame/FNA integration. Every converted class is a type of one recursive namespace, so classes can refer to each other in any order like Java. |
| [GDScript](https://docs.godotengine.org/en/stable/getting_started/step_by_step/scripting_languages.html) / [repo](https://github.com/AllBinary/AllBinary-Platform-GDScript) | Interpreted (Godot) | Desktop, Mobile, Web (via Godot exports) | Godot's scripting language with optional static typing. | Converter, runtime, and poms in progress; checked headless with Godot 4.7.2, which loads every script of the runtime, the dependencies, and the project so the GDScript parser and analyzer check them. The converted code is untyped, with one global `class_name` per file, single inheritance, and no exceptions or overloading, so Java exceptions are a pending exception and overloads get the parameter count in their names. Planned Godot integration. |
| [Gleam](https://gleam.run/) | Managed VM (BEAM), JS engine | Desktop (BEAM), Web (JavaScript) | Statically typed functional language targeting Erlang and JavaScript. Great for multiplayer backend servers	Poor fit for client rendering/physics |  |
| [Go](https://go.dev/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Go) | Native | Desktop |  |  |
| [Groovy](https://groovy-lang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Groovy) | JVM | Desktop (J2SE) |  |  |
| [Haskell](https://www.haskell.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Haskell) | Native | Desktop | GHC |  |
| [Haxe](https://haxe.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Haxe) | Native (via the hxcpp C++ target) | Desktop | Haxe can also target JS, JVM, and HashLink. |  |
| [Janet](https://janet-lang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Janet) | Interpreted (embedded) | Desktop | Embeddable, with a simple C FFI. |  |
| [Julia](https://julialang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Julia) | JIT (LLVM) | Desktop | Designed for numerical computing. Code compiles on first call, so startup is slower; standalone executables via PackageCompiler or `juliac`. |  |
| [Lean 4](https://lean-lang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Lean) | Native (compiles through C) | Desktop | Functional programming language and proof assistant with dependent types, reference counting, and C interoperability. |  |
| [Lisp](https://github.com/sbcl/sbcl) / [repo](https://github.com/AllBinary/AllBinary-Platform-Lisp-with-native-call-support) | Native (e.g. SBCL) | Desktop | Code can be redefined while the program runs (REPL). Garbage collected; smaller library ecosystem. |  |
| [Lua](https://www.lua.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Lua) | Interpreted | Desktop |  |  |
| [Nim](https://nim-lang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Nim) | Native (compiles through C) | Desktop | Raylib/SDL2 bindings. Deterministic memory management (ARC/ORC). Smaller community than C++/C#. |  |
| [OCaml](https://ocaml.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Ocaml) | Native | Desktop | ocamlopt (bytecode is also possible) |  |
| [Object Pascal](https://www.freepascal.org/docs.html) / [repo](https://github.com/AllBinary/AllBinary-Platform-ObjectPascal) | Native | Desktop | Free Pascal |  |
| [Odin](https://odin-lang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Odin) | Native | Desktop | Data-oriented programming; maintained OpenGL, SDL2, and raylib bindings in its vendor library. | Converter, runtime, and poms in progress; checked with `odin check` (dev-2026-11). |
| [Perl](https://www.perl.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Perl) | Interpreted | Desktop |  |  |
| [Python](https://www.python.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Python) | Interpreted | Desktop |  |  |
| [Racket](https://racket-lang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Racket) | Native (Chez Scheme backend) | Desktop | Includes 2D graphics libraries. Standalone apps via `raco exe` and `raco distribute`. |  |
| [Ruby](https://www.ruby-lang.org/en/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Ruby) | Interpreted | Desktop |  |  |
| [Rust](https://www.rust-lang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Rust) | Native | Desktop |  |  |
| [Scala](https://www.scala-lang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Scala) | JVM | Desktop (J2SE) | Scala Native is a possible later step. | |
| [Squirrel](https://www.squirrel-lang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Squirrel) | Managed VM (embedded bytecode) | Desktop (inside a host engine) | Lightweight object-oriented scripting with a C API for embedding in games. | Converter, runtime, and poms in progress; compiled and loaded with the sqcheck host (Squirrel 3.2, 64 bit integers and double floats). |
| [Tcl](https://www.tcl-lang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Tcl) | Interpreted | Desktop |  |  |
| [Wren](https://wren.io/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Wren) | Interpreted (embedded) | Desktop (inside a host engine) | Designed for embedding in game engines. Requires a host C/C++ engine. |  |
| [Zig](https://ziglang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Zig) | Native | Desktop |  |  |

### Tier 4 – AllBinaryBase builds (with 5 private libraries)

| Language/AllBinary Repo | Language type | Targets | Notes | Other Progress |
|-------------------------|---------------|---------|----------------|----------------|
| [ActionScript](https://airsdk.dev/reference/actionscript/3.0/) / [repo](https://github.com/AllBinary/AllBinary-Platform-ActionScript) | Managed VM (AIR) | Desktop and Mobile (AIR), Web (Ruffle) | Flash Player reached end of life in 2020; AIR is now maintained by Harman. Matches the Flash/ActionScript target in the diagram. |  |
| [COBOL](https://gnucobol.sourceforge.io/) / [repo](https://github.com/AllBinary/AllBinary-Platform-COBOL) | Native | Desktop | GnuCOBOL compiles through C. |  |
| [Elm](https://guide.elm-lang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Elm) | JS engine | Web | Pure functional; game state is updated through messages (The Elm Architecture). |  |
| [FORTRAN](https://fortran-lang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-FORTRAN) | Native | Desktop |  |  |
| [Goose](https://github.com/aardappel/goose) / [repo](https://github.com/AllBinary/AllBinary-Platform-Goose) | Native (compiles through C) | Desktop | C output builds with MSVC, GCC, or Clang; bundled TinyCC backend for in-process execution. |  |
| [Grain](https://grain-lang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Grain) | JS engine (WASM) | Web | Compiles to WebAssembly; runs in the browser's WASM engine, not as JavaScript. Young language and ecosystem. |  |
| [Objective-C](https://developer.apple.com/library/archive/documentation/Cocoa/Conceptual/ProgrammingWithObjectiveC/Introduction/Introduction.html) / [repo](https://github.com/AllBinary/AllBinary-Platform-Objective-C) | Native | Desktop (macOS first), Mobile (iOS) |  |  |
| [Swift](https://www.swift.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Swift) | Native | Mobile (iOS, Android), Desktop | Compiles natively on macOS, Linux, and Windows. Android is supported through the official Swift SDK for Android. |  |
| [Vala](https://vala.dev/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Vala) | Native (compiles through C) | Desktop (Linux first) | Built on GObject; mostly used with GNOME. |  |

### Planned – Initial commit not yet added or in progress

Grouped by language type. For planned languages

| Language/AllBinary Repo | Language type | Targets | Notes | Other Progress |
|-------------------------|---------------|---------|----------------|----------------|
| [Crystal](https://crystal-lang.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Crystal) | Native (LLVM) | Desktop | Ruby-inspired syntax with static typing and type inference. | Converter, runtime, and poms in progress; checked with `crystal build --no-codegen` (Crystal 1.22.3) and an entry that calls every method (Crystal only type checks the code a program reaches). A Java class is a Crystal class, an interface a module, and references are nilable. |
| [ASM](https://www.nasm.us/doc/) / [repo](https://github.com/AllBinary/AllBinary-Platform-ASM) | Native | Desktop | Platform/ISA specific |  |
| [Scheme](https://www.r7rs.org/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Scheme) | Native (via Chicken/Gambit C) | Desktop | Many separate implementations; Chicken and Gambit compile through C. |  |
| [Beef](https://www.beeflang.org/docs/language-guide/interop/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Beef) | Native | Desktop | Static/dynamic library linking through `extern`, DLL imports, and C linkage. |  |
| [BlitzMax](https://www.blitzmax.org/docs/en/language/interfacing_with_c/) / [repo](https://github.com/AllBinary/AllBinary-Platform-BlitzMax) | Native | Desktop | `Import` and `Extern` integrate C source and functions. |  |
| [C3](https://c3-lang.org/language-common/cinterop/) / [repo](https://github.com/AllBinary/AllBinary-Platform-C3) | Native | Desktop | C ABI compatibility, `extern` declarations, and static/dynamic linking. |  |
| [V](https://docs.vlang.io/v-and-c.html) / [repo](https://github.com/AllBinary/AllBinary-Platform-V) | Native (C backend) | Desktop | Calls C functions through declarations, headers, and linked libraries. |  |
| [Factor](https://docs.factorcode.org/content/article-alien.html) / [repo](https://github.com/AllBinary/AllBinary-Platform-Factor) | Native (optimizing compiler) | Desktop | Built-in C library interface, including native callbacks. |  |
| [Gforth (Forth)](https://www.complang.tuwien.ac.at/forth/gforth/Docs-html/Declaring-C-Functions.html) / [repo](https://github.com/AllBinary/AllBinary-Platform-Forth) | Implementation-dependent (Gforth) | Desktop | Implementation-specific support; Gforth provides C function declarations and linking. |  |
| [Raku (Rakudo)](https://docs.raku.org/language/nativecall) / [repo](https://github.com/AllBinary/AllBinary-Platform-Raku) | Managed VM (Rakudo/MoarVM) | Desktop | `NativeCall` calls functions in native libraries. |  |
| [R](https://cloud.r-project.org/doc/manuals/r-release/R-exts.html) / [repo](https://github.com/AllBinary/AllBinary-Platform-R) | Interpreted | Desktop | `.C`, `.Call`, and related interfaces integrate compiled native code. |  |
| [FreeBASIC](https://www.freebasic.net/wiki/KeyPgExtern) / [repo](https://github.com/AllBinary/AllBinary-Platform-FreeBASIC) | Native | Desktop | C library interoperability through external declarations and linkage. |  |
| [Nelua](https://nelua.io/overview/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Nelua) | Native (compiles through C) | Desktop | `<cimport>` declarations integrate C functions and headers. [MIT](https://nelua.io/faq/). |  |
| [ATS (Postiats)](https://ats-lang.sourceforge.net/DOCUMENT/INT2PROGINATS/HTML/c2016.html) / [repo](https://github.com/AllBinary/AllBinary-Platform-ATS) | Native (compiles through C) | Desktop | Direct C interoperability, external functions, and embedded C code. [ATS/Postiats: GPLv3](https://ats-lang.sourceforge.net/DOWNLOAD/). |  |
| [OpenEuphoria](https://github.com/OpenEuphoria/euphoria) / [repo](https://github.com/AllBinary/AllBinary-Platform-OpenEuphoria) | Interpreted; Native via C translator | Desktop | Calls dynamically loaded C routines and supports callbacks. [OpenEuphoria: open source](https://github.com/OpenEuphoria/euphoria). |  |
| [Ring](https://ring-lang.github.io/doc1.26/faq.html) / [repo](https://github.com/AllBinary/AllBinary-Platform-Ring) | Interpreted (VM) | Desktop | C/C++ extensions expose native functionality. [MIT](https://github.com/ring-lang/ring). |  |
| [GNU Modula-2](https://gcc.gnu.org/onlinedocs/gcc-13.2.0/gm2/Interface-to-C.html) / [repo](https://github.com/AllBinary/AllBinary-Platform-Modula-2) | Native (GNU Modula-2) | Desktop | GNU Modula-2 supports C calling conventions through `DEFINITION MODULE FOR "C"`. [GNU compiler: GPL](https://gcc.gnu.org/gccmission.html). |  |
| [Modula-3 (CM3)](https://github.com/modula3/cm3) / [repo](https://github.com/AllBinary/AllBinary-Platform-Modula-3) | Native (CM3) | Desktop | Supports C interoperability, including interfaces to standard C libraries. [CM3: open source](https://github.com/modula3/cm3). |  |
| [Harbour](https://github.com/harbour/core) / [repo](https://github.com/AllBinary/AllBinary-Platform-Harbour) | Managed VM | Desktop | Supports invoking native functions through pointers. [Native call implementation](https://github.com/harbour/core/blob/master/src/rtl/hbdyn.c). [Licensing FAQ](https://harbour.github.io/faq). |  |
| [Umka](https://github.com/vtereshkov/umka-lang/blob/master/doc/api.md) / [repo](https://github.com/AllBinary/AllBinary-Platform-Umka) | Interpreted (embedded VM) | Desktop (inside a host application) | A C host registers functions that Umka scripts can call using `umkaAddFunc()`. [BSD-2-Clause](https://github.com/vtereshkov/umka-lang). |  |
| [Uiua](https://github.com/uiua-lang/uiua/blob/main/changelog.md) / [repo](https://github.com/AllBinary/AllBinary-Platform-Uiua) | Interpreted | Desktop | The `&ffi` operation calls native functions and is still evolving. The project publishes [raylib bindings](https://github.com/uiua-lang/rayua). [MIT](https://github.com/uiua-lang/uiua). |  |
| [CBQN (BQN)](https://mlochbaum.github.io/BQN/doc/ffi.html) / [repo](https://github.com/AllBinary/AllBinary-Platform-CBQN) | Interpreted (CBQN) | Desktop | CBQN implements `•FFI` for calling functions in native shared libraries. [CBQN licensing](https://github.com/dzaima/CBQN#license). |  |
| [Fennel](https://github.com/bakpakin/Fennel) / [repo](https://github.com/AllBinary/AllBinary-Platform-Fennel) | Lua runtime | Desktop | Compiles to Lua and can use [native modules exposed through Lua](https://www.lua.org/manual/5.4/manual.html#pdf-package.loadlib). MIT. |  |
| [ChaiScript](https://chaiscript.com/examples.html) / [repo](https://github.com/AllBinary/AllBinary-Platform-ChaiScript) | Interpreted (embedded) | Desktop (inside a C++ host) | A C++ host can expose functions and types to scripts. [License](https://github.com/ChaiScript/ChaiScript/blob/develop/LICENSE). |  |
| [Luau](https://luau.org/api/) / [repo](https://github.com/AllBinary/AllBinary-Platform-Luau) | Managed VM (embedded) | Desktop (inside a C/C++ host) | A C or C++ host can register native functions that scripts can call. [MIT](https://github.com/luau-lang/luau). |  |
| [Carp](https://github.com/carp-lang/Carp/blob/master/docs/CInterop.md) / [repo](https://github.com/AllBinary/AllBinary-Platform-Carp) | Native (compiles through C) | Desktop | A Lisp designed for real-time applications. It can call C functions and ships [SDL bindings](https://github.com/carp-lang/Carp/blob/master/core/SDL.carp). |  |
| [MiniScript (raylib-miniscript / MSRLWeb)](https://github.com/JoeStrout/raylib-miniscript) / [repo](https://github.com/AllBinary/AllBinary-Platform-MiniScript) | Interpreted (embedded) | Desktop, Web/WASM | Has native raylib bindings. [MSRLWeb](https://github.com/JoeStrout/MSRLWeb) runs games in the browser via raylib and WebAssembly. MIT. |  |
| [Jolt](https://github.com/jolt-lang/jolt) / [repo](https://github.com/AllBinary/AllBinary-Platform-Jolt) | Native (Chez Scheme) | Desktop | A Clojure dialect with C ABI interoperability and [raylib examples](https://github.com/jlt-commons/raylib-jlt). EPL 2.0. |  |
| [Cyber](https://github.com/fubark/cyber) / [repo](https://github.com/AllBinary/AllBinary-Platform-Cyber) | Managed VM | Desktop | Embeddable scripting language with [raylib bindings](https://github.com/fubark/ray-cyber). MIT. |  |
| [CLIPS](https://www.clipsrules.net/documentation/v641/apg641.pdf) / [repo](https://github.com/AllBinary/AllBinary-Platform-CLIPS) | Rule engine | Desktop (inside a host) | C hosts can register callable functions. [CLIPSraylib](https://github.com/mrryanjohnston/CLIPSraylib) exposes graphics and input. |  |
| [ElfScript](https://github.com/ohmtal/ElfScript) / [repo](https://github.com/AllBinary/AllBinary-Platform-ElfScript) | Interpreted (embedded) | Desktop | Embeddable scripting language with game engine examples. The [raylib bindings index](https://github.com/raysan5/raylib/blob/master/BINDINGS.md) lists a native binding. |  |
| [Elle](https://github.com/acquitelol/elle) / [repo](https://github.com/AllBinary/AllBinary-Platform-Elle) | Native (QBE) | Desktop | Compiles through QBE and has a [raylib module](https://github.com/acquitelol/elle/blob/rewrite/std/raylib.le). |  |
| [Matte](https://github.com/jcorks/matte) / [repo](https://github.com/AllBinary/AllBinary-Platform-Matte) | Interpreted (embedded) | Desktop | The [raylib module](https://github.com/jcorks/raylib-matte) exposes native graphics and input functions. |  |
| [Oberon-2 (Ofront+)](https://github.com/RobertFlexx/Rayberon) / [repo](https://github.com/AllBinary/AllBinary-Platform-Oberon-2) | Native (via Ofront+) | Desktop | Rayberon binds raylib for native 2D and 3D graphics. |  |
| [Component Pascal (Ofront+)](https://github.com/RobertFlexx/Rayberon) / [repo](https://github.com/AllBinary/AllBinary-Platform-Component-Pascal) | Native (via Ofront+) | Desktop | Rayberon provides bindings for the Component Pascal dialect through Ofront+. |  |
| [Scopes](https://github.com/Fundament-Software/scopes) / [repo](https://github.com/AllBinary/AllBinary-Platform-Scopes) | Native | Desktop | The [raylib wrapper](https://github.com/salotz/raylib-scopes) demonstrates native graphics integration. |  |
| [SmallBASIC](https://smallbasic.github.io/pages/plugins_raylib.html) / [repo](https://github.com/AllBinary/AllBinary-Platform-SmallBASIC) | Interpreted | Desktop | A native raylib plugin exposes rendering, input, and audio. [Interpreter](https://github.com/smallbasic/SmallBASIC). |  |
| [Solod](https://github.com/solod-dev/solod) / [repo](https://github.com/AllBinary/AllBinary-Platform-Solod) | Native (via C) | Desktop | Provides [raylib bindings](https://github.com/solod-dev/raylib) for native graphics. |  |
| [Wave](https://github.com/wavefnd/Wave) / [repo](https://github.com/AllBinary/AllBinary-Platform-Wave) | Native | Desktop | Has [native raylib bindings](https://github.com/wavefnd/raylib-wave). |  |
| [Mach](https://github.com/briar-systems/mach) / [repo](https://github.com/AllBinary/AllBinary-Platform-Mach) | Native | Desktop | Systems language with [raylib bindings](https://github.com/Angluca/mach-raylib). |  |
| [Jule](https://github.com/julelang/jule) / [repo](https://github.com/AllBinary/AllBinary-Platform-Jule) | Native (via C++) | Desktop | Supports C/C++ interoperability and has [community raylib bindings](https://github.com/SabeDoesThings/rayjule). |  |
| [XD Pascal](https://github.com/vtereshkov/xdpw) / [repo](https://github.com/AllBinary/AllBinary-Platform-XD-Pascal) | Native | Desktop (Windows) | Embeddable Pascal dialect with an [older raylib binding](https://github.com/vtereshkov/raylib-xdpw). |  |
| [F*](https://github.com/FStarLang/FStar) / [repo](https://github.com/AllBinary/AllBinary-Platform-FStar) | Native via Low* and KaRaMeL | Desktop | [KaRaMeL](https://github.com/FStarLang/karamel) compiles the Low* subset to C. The [raylib index](https://github.com/raysan5/raylib/blob/master/BINDINGS.md) lists F* bindings. |  |
| [MLton (Standard ML)](https://github.com/MLton/mlton) / [repo](https://github.com/AllBinary/AllBinary-Platform-Standard-ML) | Native (MLton) | Desktop | Implementation-specific support; MLton supports calling C and exporting functions to C. |  |

### Possible Future - TBD

| Language/AllBinary Repo | Language type | Targets | Notes | Other Progress |
|-------------------------|---------------|---------|----------------|----------------|
| [Bend](https://github.com/bendlang/bend) / [repo](https://github.com/AllBinary/AllBinary-Platform-Bend) | Native (CPU/GPU backends) | Desktop (Linux and macOS; Windows via WSL) | Functional language focused on automatic parallelism; current Bend 2 also supports dependent types and proofs. | Game runtime and graphics integration would need evaluation. |
| [Hare](https://harelang.org/documentation/usage/system-libraries.html) | Native | Desktop (Linux and BSD; Windows via WSL is an unsupported workaround) | C FFI and mixed Hare/C programs. [Upstream platform policy](https://harelang.org/documentation/faq.html). |  |
| [Kit](https://github.com/kitlang/kit) | Native (compiles through C) | Desktop (Linux and macOS; Windows via WSL for the current raylib toolchain) | A game-oriented language that imports C headers and uses their functions and types. The [experimental Rust compiler](https://github.com/kitlang-dev/kitc) has a documented [raylib package](https://kit-lang.org/docs/2026.7.12/packages/kit-raylib.html). [Current compiler platforms](https://kit-lang.org/faqs.html). The [original compiler](https://github.com/kitlang/kit#windows-setup) supports MinGW-w64. |  |
| [Mojo](https://mojolang.org/docs/manual/get-started/) | Native (MLIR/LLVM) | Desktop (Linux and macOS; Windows via WSL) | Python-inspired syntax with static typing, ownership, and Python/C interoperability. |  |
| [Never](https://github.com/never-lang/never) | Managed VM (embedded) | Desktop (Linux; Windows via WSL for the documented Linux build) | Statically typed functional language with an [older raylib binding](https://github.com/never-lang/raylib-never). [Upstream build requirements](https://github.com/never-lang/never#prerequisites). |  |
| [Sunder](https://github.com/ashn-dot-dev/sunder) | Native | Desktop (Linux and ARM64 macOS; Windows via WSL) | Systems language with [native raylib bindings](https://github.com/ashn-dot-dev/raylib-sunder). [Supported platforms](https://github.com/ashn-dot-dev/sunder#dependencies). |  |
| [PHP](https://www.php.net/manual/en/intro-whatis.php) / [repo](https://github.com/AllBinary/AllBinary-Platform-PHP) | Interpreted (optional JIT) | Desktop (CLI), Server (planned) | PHP 8+ scripting through the CLI; OPcache provides optional JIT compilation. |  |
| [Scratch (via TurboWarp)](https://docs.turbowarp.org/development/extensions/unsandboxed) | Visual programming (Scratch-compatible VM) | Desktop, Web | TurboWarp JavaScript extensions expose browser APIs as blocks. The [extension gallery](https://extensions.turbowarp.org/) includes GPU-accelerated Simple 3D. [Runtime source](https://github.com/TurboWarp/scratch-vm). |  |
| [Visual Basic .NET](https://learn.microsoft.com/en-us/dotnet/visual-basic/language-reference/statements/declare-statement) | Managed VM (.NET CLR) | Desktop | `Declare` and P/Invoke call native DLL functions. [Roslyn: MIT](https://github.com/dotnet/roslyn). |  |
| [AutoHotkey](https://github.com/AutoHotkey/AutoHotkeyDocs/blob/v2/docs/lib/DllCall.htm) | Interpreted automation language | Desktop (Windows) | `DllCall` invokes native functions from DLLs, including Win32 APIs. [GPLv2 implementation](https://github.com/AutoHotkey/AutoHotkey). |  |
| [Cython](https://cython.readthedocs.io/en/latest/src/userguide/external_C_code.html) | Native extension (via C/C++) | Desktop (CPython) | Python-derived language with `cdef extern` declarations for calls into linked C libraries. [Apache 2.0 compiler](https://github.com/cython/cython/blob/master/LICENSE.txt). |  |
| [QML (Qt)](https://doc.qt.io/qt-6/qtqml-cppintegration-exposecppattributes.html) | Declarative UI language (Qt) | Desktop, Mobile, Embedded | Calls registered C++ methods exposed through `Q_INVOKABLE` or public slots. [Qt Qml licensing](https://doc.qt.io/qt-6/qtqml-index.html). |  |
| [Ren'Py script](https://www.renpy.org/doc/html/python.html) | Interpreted (Ren'Py engine) | Desktop | Python blocks can call compiled APIs exposed through the embedded Python runtime. [Engine licenses](https://www.renpy.org/doc/html/license.html). |  |
| [Processing (Java mode)](https://www.processing.org/tutorials/p3d/) | JVM | Desktop | The sketch language uses JOGL for native OpenGL rendering through `P2D` and `P3D`. Sketches can also use Java libraries. [Source and licenses](https://github.com/processing/processing4). |  |
| [ClojureScript](https://clojurescript.org/) | JS engine | Web | Compiles to JavaScript and can use WebGL through JavaScript interop. [thi.ng/geom WebGL examples](https://github.com/thi-ng/geom#opengl--webgl). |  |
| [CoffeeScript](https://coffeescript.org/) | JS engine | Web | Compiles to JavaScript and interoperates with JS libraries, allowing WebGL calls through JavaScript APIs. [MIT](https://github.com/jashkenas/coffeescript). |  |
| [PICO-8 Lua (FAKE-08)](https://github.com/jtothebell/fake-08/blob/master/source/vm.cpp) | Game scripting (Lua VM) | Desktop, Handheld | The MIT-licensed [FAKE-08 runtime](https://github.com/jtothebell/fake-08) implements PICO-8 game APIs through C++ functions registered with its Lua VM. |  |
| [Slint](https://docs.slint.dev/latest/docs/slint/guide/getting-started/) | Declarative UI language | Desktop, Mobile, Embedded, Web/WASM | UI callbacks can invoke compiled Rust or C++ functions supplied by the host. [GPLv3 option](https://github.com/slint-ui/slint). |  |
| [TwineScript (SugarCube)](https://www.motoslave.net/sugarcube/2/docs/#macros-macro-script) | JS engine | Web | SugarCube's script macro runs JavaScript or TwineScript in the browser and can call WebGL through JavaScript. [BSD-2-Clause implementation](https://github.com/tmedwards/sugarcube-2). |  |
| [Snap!](https://docs.snap.berkeley.edu/snap-manual.pdf) | Visual programming (JS engine) | Web | The `JavaScript function` block can wrap browser WebGL calls when JavaScript extensions are enabled. AGPL. |  |
| [Pure Data (Pd)](https://github.com/pure-data/externals-howto) | Visual dataflow (Pd runtime) | Desktop | Patches can use compiled C externals. The [Gem extension](https://github.com/umlaeute/Gem) exposes OpenGL rendering. |  |
| [GNU Guile (Scheme)](https://www.gnu.org/software/guile/manual/guile.pdf) | Managed VM (Guile) | Desktop | The foreign-library API loads shared libraries and exposes callable C functions. [GNU project source and downloads](https://www.gnu.org/software/guile/). |  |
| [Chez Scheme](https://www.scheme.com/csug8/foreign.html) | Native (Chez Scheme compiler) | Desktop | `foreign-procedure` declares calls to C-compatible functions. [Apache 2.0 implementation](https://github.com/cisco/ChezScheme). |  |
| [Pharo (Smalltalk)](https://books.pharo.org/booklet-uffi/) | Managed VM (Pharo) | Desktop | Unified FFI calls native libraries. [Pharo: MIT](https://pharo-project.org/). |  |
| [ReScript](https://www.rescript-lang.org/docs/manual/external/) | JS engine | Web | JavaScript `external` bindings provide a route to WebGL and JS graphics libraries; bindings would need integration. [LGPL compiler; MIT runtime](https://github.com/rescript-lang/rescript#-license). |  |
| [PureScript](https://github.com/purescript/documentation/blob/master/guides/FFI.md) | JS engine | Web | JavaScript FFI provides a route to WebGL bindings; bindings would need integration. [Open source](https://github.com/purescript/purescript). |  |
| [Squeak (OpenSmalltalk VM)](https://wiki.squeak.org/squeak/1414) | Managed VM | Desktop | Squeak's foreign function interface calls functions in native shared libraries. [Open-source runtime](https://github.com/OpenSmalltalk/opensmalltalk-vm). |  |
| [Imba](https://github.com/imba/imba) | JS engine | Web | MIT-licensed web UI language. Compiles to JavaScript and can call browser WebGL APIs through JavaScript interop. |  |
| [Rhai](https://rhai.rs/book/rust/functions.html) | Interpreted (embedded) | Desktop (inside a Rust host) | Scripts can call Rust functions registered by the host. A [Bevy plugin](https://github.com/makspll/bevy_mod_scripting) provides game engine integration. |  |
| [ReasonML (Melange)](https://reasonml.github.io/docs/en/interop) | JS engine (via Melange) | Web | Melange JavaScript bindings can expose browser WebGL methods and graphics libraries such as Three.js. [Reason toolchain license](https://github.com/reasonml/reason). |  |
| [Adventure Game Studio Script](https://adventuregamestudio.github.io/ags-manual/EnginePluginRun-timeAPI.html) | Managed VM (AGS engine) | Desktop | Native plugins can register functions that point-and-click adventure scripts call. [Engine source](https://github.com/adventuregamestudio/ags). |  |
| [Eiffel (EiffelStudio)](https://www.eiffel.org/doc/solutions/Interfacing_with_C_and_C%2B%2B) | Native | Desktop | External routines interfacing with C and C++. |  |
| [PascalABC.NET](https://pascalabc.net/downloads/pabcnethelp/topics/Common/features.html) | Managed VM (.NET CLR) | Desktop | The `CallNative.pas` example demonstrates calling functions in native DLLs. [Open-source compiler licensing](https://www.pascalabc.net/en/license/). |  |
| [ECL (Embeddable Common Lisp)](https://ecl.common-lisp.dev/static/manual/Foreign-Function-Interface.html) | Interpreted or native (via C) | Desktop | ECL calls native libraries through its FFI and supports inline C with `ffi:c-inline`. [Project source and licensing](https://ecl.common-lisp.dev/main.html). |  |
| [QB64 Phoenix Edition](https://qb64.com/wiki/DECLARE-DYNAMIC-LIBRARY.html) | Native (via C++) | Desktop | Extended QBasic with `DECLARE DYNAMIC LIBRARY` for native shared libraries. [QB64 Phoenix Edition source and licenses](https://github.com/QB64-Phoenix-Edition/QB64pe). |  |
| [GNU CLISP (Common Lisp)](https://clisp.sourceforge.io/beta/impnotes/dffi.html) | Managed VM | Desktop | The dynamic FFI can bind native functions with `FFI:DEF-CALL-OUT`. [GNU CLISP project](https://clisp.sourceforge.io/summary.html). |  |
| [Hy](https://hylang.org/hy/doc/v0.28.0/interop) | CPython runtime | Desktop | Calls Python modules directly, including [ctypes](https://docs.python.org/3/library/ctypes.html) for native shared-library functions. [Source and license](https://github.com/hylang/hy/blob/master/LICENSE). |  |
| [XGo (LLGo backend)](https://github.com/goplus/xgo#importing-cc-and-python-libraries) | Native (LLGo) | Desktop | XGo uses LLGo to import C and C++ libraries and call their native functions. Apache 2.0. |  |
| [Red](https://www.red-lang.org/) | Interpreted / Native | Desktop | Native integration through its Red/System layer. |  |
| [Gambas](https://gambaswiki.org/wiki/howto/extern) | Interpreted (VM) | Desktop (Linux first) | `EXTERN` and `LIBRARY` call functions in C shared libraries. [GPLv2 or later](https://gambaswiki.org/wiki/doc/license). |  |
| [QuakeC (Quake engine)](https://github.com/graphitemaster/gmqcc) | Managed VM (game engine) | Desktop (Quake-compatible engine) | Gameplay code calls native engine built-ins for operations such as spawning entities and playing sounds. [Quake engine source](https://github.com/id-Software/Quake). |  |
| [Civet](https://github.com/DanielXMoore/Civet) | JS engine | Web | An MIT-licensed TypeScript superset that can reach WebGL through browser JavaScript APIs. |  |
| [Teal](https://github.com/teal-language/tl) | Lua runtime | Desktop (including LÖVE) | A typed Lua dialect that can use native APIs exposed to Lua. [LÖVE game example](https://github.com/teal-language/tl/discussions/637). MIT. |  |
| [Blitz3D](https://github.com/blitz-research/blitz3d) | Native (Blitz BASIC compiler) | Desktop (Windows) | Userlibs use `.decls` files to expose native DLL functions. [Example game source](https://github.com/Regalis11/scpcb/blob/master/Instructions.txt). zlib/libpng. |  |
| [Pyret](https://pyret.org/docs/latest/Internals.html) | JS engine | Desktop (Node.js), Web | JavaScript modules can expose functions to Pyret programs. A browser module can wrap WebGL calls. [Compiler source](https://github.com/brownplt/pyret-lang). |  |
| [MoonScript (LÖVE)](https://moonscript.org/guides/making-games-with-love.html) | Lua runtime | Desktop (via LÖVE) | Compiles to Lua and can call the graphics and input APIs exposed by the LÖVE game framework. [MIT compiler](https://moonscript.org/). |  |
| [Terra](https://terralang.org/api.html) | Native (embedded in Lua) | Desktop | `terralib.includec()` imports C declarations for calling native functions. [License](https://github.com/terralang/terra/blob/master/release/share/terra/LICENSE.txt). |  |
| [Pawn](https://www.compuphase.com/pawn/pawnfeatures.htm) | Managed VM (embedded) | Desktop (inside a host) | The AMX API registers native functions supplied by the host. [Implementation](https://github.com/compuphase/pawn). |  |
| [BBC BASIC (BBCSDL)](https://www.bbcbasic.co.uk/bbcsdl/manual/bbcsdle.html) | Interpreted (BBCSDL) | Desktop, Mobile, Web/WASM | On native BBCSDL builds, `SYS` calls SDL or functions in external shared libraries. [Zlib-licensed implementation](https://github.com/rtrussell/BBCSDL). |  |
| [ZScript (UZDoom)](https://github.com/zdoom-docs/stable/blob/master/language.md) | Managed VM (game engine) | Desktop (UZDoom) | Calls native engine functions and works with native engine types. [Open-source implementation](https://github.com/UZDoom/UZDoom). |  |
| [Dream Maker (OpenDream)](https://github.com/OpenDreamProject/OpenDream/wiki/Differences-Between-OpenDream-and-BYOND#external-dlls) | Game scripting (OpenDream) | Desktop, Server | The MIT-licensed [OpenDream runtime](https://github.com/OpenDreamProject/OpenDream) supports external native libraries built for its 64-bit ABI. |  |
| [Hot Soup Processor (HSP3)](https://www.onionsoft.net/hsp/v36en/doclib/hspprog.htm) | Interpreted | Desktop (DLL calls on Windows) | Scripts can call Windows DLL functions using `#uselib` and `#func`. [OpenHSP: BSD-3-Clause](https://github.com/onitama/OpenHSP). |  |
| [GNU Smalltalk](https://www.gnu.org/software/smalltalk/manual/html_node/C-callout.html) | Managed VM | Desktop | C callouts and the `DLD` class expose functions from native libraries. [Runtime license](https://www.gnu.org/software/smalltalk/manual/gst.html). |  |
| [Clozure CL (Common Lisp)](https://ccl.clozure.com/docs/build/ccl.html) | Native | Desktop | Its foreign function interface calls external functions using the platform C ABI. [Project source](https://github.com/Clozure/ccl). |  |
| [Roc](https://www.roc-lang.org/docs/main/langref/platforms/) | Native | Desktop | A native platform host implements operations that Roc code calls through the platform API. [Project repository](https://github.com/roc-lang/roc). |  |
| [Inko](https://github.com/inko-lang/inko/blob/main/CHANGELOG.md) | Native | Desktop | Supports calling functions from C libraries. [MPL-2.0 toolchain](https://github.com/inko-lang/inko). |  |
| [Gauche (Scheme)](https://www.practical-scheme.net/vault/gauche-gl-refe.html) | Managed VM | Desktop | The Gauche-gl extension exposes native OpenGL functions to Scheme. [BSD-style runtime license](https://www.practical-scheme.net/gauche/download.html). |  |
| [MIT/GNU Scheme](https://www.gnu.org/software/mit-scheme/documentation/stable/mit-scheme-ffi.pdf) | Native or interpreted | Desktop | Its C FFI uses `.cdecl` declarations to bind native functions and data. |  |
| [Neko](https://nekovm.org/doc/ffi/) | Managed VM | Desktop | Neko programs call native primitives exposed through C wrappers. [Source and license](https://github.com/HaxeFoundation/neko). |  |
| [Checked C](https://github.com/checkedc/checkedc) | Native (Clang/LLVM) | Desktop | A bounds-checked C dialect with interoperability for existing C libraries. The project provides a specification and compiler. [Project license](https://github.com/checkedc/checkedc/blob/main/LICENSE.TXT). |  |
| [Berry](https://github.com/berry-lang/berry_mapping) | Interpreted (embedded VM) | Desktop (inside a host engine) | Embedded scripting with C native functions and callbacks. |  |
| [Jakt](https://github.com/SerenityOS/jakt#inline-c) | Native (compiles through C++) | Desktop | Inline `cpp` blocks let Jakt code call existing C++ functions. |  |
| [Mun](https://mun-lang.org/blog/2020/05/02/this-month-april/) | Native | Desktop (via the Mun runtime) | `extern` declarations let Mun code call registered host functions. The runtime supports hot reloading. [MIT or Apache 2.0](https://github.com/mun-lang/mun). |  |
| [Vale](https://vale.dev/guide/externs) | Native | Desktop | `extern` declarations call C functions and exports can be called from C. [Apache 2.0 compiler](https://github.com/ValeLang/Vale/blob/master/LICENSE). |  |
| [Objective-J](https://www.cappuccino.dev/learn/objective-j.html) | JS engine | Web | The Cappuccino language interoperates directly with JavaScript, which gives browser code access to WebGL. [LGPL](https://www.cappuccino.dev/learn/). |  |
| [Rune](https://docs.rune.build/languages/rust/) | Managed VM (embedded) | Desktop (inside a Rust host) | Scripts can call Rust functions exported through native modules. [Source repository](https://github.com/rune-rs/rune). |  |
| [Quirrel](https://quirrel.io/doc/embedding/bindings.html) | Managed VM (embedded) | Desktop (inside a C++ host) | Squirrel-derived game scripting language with Sqrat bindings for native C++ functions and types. [Source](https://github.com/GaijinEntertainment/quirrel). |  |
| [Daslang (formerly daScript)](https://daslang.io/doc/reference/embedding/external_modules.html) | Interpreted; Native via C++ AOT | Desktop | C/C++ integration through native bindings and external modules; designed for games. [BSD-3-Clause](https://github.com/GaijinEntertainment/daScript). |  |
| [Rebol 3 (Oldes)](https://github.com/Oldes/Rebol3) | Interpreted | Desktop | The Apache 2.0 licensed Oldes implementation supports C extensions. Its [FMOD extension](https://github.com/Oldes/R3-extension-FMOD) demonstrates calls to a native audio library. |  |
| [Inform 6 (Glulx with Glulxe)](https://github.com/iftechfoundation/ifarchive-if-specs/blob/main/Glulx-Inform-Tech.md) | Interactive-fiction VM (Glulx) | Desktop | Glulx stories call the host's Glk I/O and graphics functions through the native [Glulxe interpreter](https://github.com/erkyrath/glulxe). [Inform 6 compiler license](https://github.com/DavidKinder/Inform6/blob/master/licence.txt). |  |
| [GNU Pascal](https://www.gnu-pascal.org/gpc/Importing-Libraries-from-Other-Languages.html) | Native (GCC) | Desktop | `external` declarations bind functions from linked C libraries. |  |
| [Trealla Prolog (native FFI builds)](https://github.com/trealla-prolog/trealla-prolog) | Interpreted | Desktop, Mobile (Android) | The FFI calls native libraries and includes bindings for raylib, Raymath, and cairo. MIT. |  |
| [Tau Prolog](https://tau-prolog.org/documentation) | JS engine | Web, Desktop (Node.js) | The `library(js)` module invokes JavaScript functions, so browser programs can call wrappers around WebGL. [BSD-3-Clause interpreter](https://github.com/tau-prolog/tau-prolog). |  |
| [Pascal Script](https://www.remobjects.com/ps.aspx) | Managed VM (embedded) | Desktop (inside a Pascal host) | Hosts can register native Delphi functions and classes for Pascal Script code. |  |
| [Fantom](https://fantom.org/doc/docLang/JavaScript) | JVM or JS engine | Desktop, Web | Compiles to JavaScript. JavaScript peers can wrap browser APIs such as WebGL. [Native peers](https://www.fantom.org/doc/docLang/Natives). |  |
| [Ceylon (JavaScript backend)](https://www.saylong.org/documentation/tour/dynamic/) | JVM or JS engine | Desktop, Web | The JavaScript backend can access browser APIs through dynamic interop. [The compiler is archived](https://github.com/eclipse-archived/ceylon). |  |
| [TorqueScript (Torque3D)](https://docs.torque3d.org/for-programmers/scripting/torquescript/what-is-torquescript) | Interpreted (game engine) | Desktop (Torque3D) | Can call C++ console functions exposed by the [MIT-licensed engine](https://github.com/TorqueGameEngines/Torque3D). |  |
| [Squint](https://github.com/squint-cljs/squint) | JS engine | Web | This ClojureScript dialect compiles to JavaScript. Its interop can reach browser WebGL or libraries such as Three.js through application bindings. |  |
| [jank](https://github.com/jank-lang/jank) | Native (LLVM) | Desktop | A native Clojure dialect with C++ interoperability for calling functions from existing libraries. |  |
| [Open Dylan](https://package.opendylan.org/opendylan/library-reference/c-ffi/index.html) | Native (Open Dylan) | Desktop | Open Dylan's `c-ffi` library describes and calls C interfaces. |  |
| [Pike](https://pike.lysator.liu.se/docs/man/chapter_30.html) | Interpreted (VM) | Desktop | C extension modules expose native functions and libraries. [GPL/LGPL/MPL options](https://github.com/pikelang/Pike). |  |
| [Suneido](https://suneido.com/downloads/) | Managed VM | Desktop (Windows for DLL calls) | Suneido’s DLL interface calls native libraries. The project supplies database and application UI tools. [MIT-licensed Go implementation](https://github.com/apmckinlay/gsuneido). |  |
| [Koka](https://koka-lang.github.io/koka/doc/book.html) | Native (C backend) | Desktop | The C backend can call C functions through `extern` bindings. [C FFI example](https://github.com/koka-lang/koka/discussions/664). |  |
| [Io (native branch)](https://github.com/IoLanguage/io) | Interpreted | Desktop (native branch); Web/WASM (current branch) | Native branch supports `DynLib` and C extensions; its current WebAssembly path uses host integration instead. [BSD/MIT-style](https://iolanguage.org/docs/Guide/). |  |
| [Objeck](https://www.objeck.org/doc_native_interface.html) | Managed VM | Desktop | C++ native modules expose functions to Objeck. The project provides [SDL2 bindings](https://www.objeck.org/api/latest/game.sdl2-surface.html?scrollTo=game.sdl2). [Source and license](https://github.com/objeck/objeck-lang). |  |
| [Unicon](https://unicon.sourceforge.io/up/functions.html#loadfunc) | Interpreted (VM) | Desktop | `loadfunc()` loads a C function from a shared library using the Unicon calling interface. [GPL](https://github.com/uniconproject/unicon). |  |
| [Oberon+](https://github.com/rochus-keller/Oberon) / [repo](https://github.com/AllBinary/AllBinary-Platform-OberonPlus) | Managed VM (CLI/Mono); Native via generated C | Desktop | The OBX compiler emits .NET CLI bytecode, or C99 source for native builds. Few game libraries. |  |
| [X11-Basic](https://x11-basic.sourceforge.net/X11-Basic-manual.pdf) | Interpreted or native (via C) | Desktop | `LINK`, `SYM_ADR`, and `CALL` support calling routines in shared libraries. Includes graphics and sound commands. GPLv2 or later. |  |
| [Yabasic](https://fossies.org/linux/yabasic/yabasic.htm) | Interpreted | Desktop | Its libffi-based foreign function interface calls native functions from shared libraries and DLLs. [MIT interpreter](https://github.com/marcIhm/yabasic). |  |
| [Amiga E (original EC compiler)](https://amiga-e.com/docs/language/beginner-modules.html) | Native (68k) | Desktop (Amiga) | `MODULE` declarations and `OpenLibrary` let programs call native Amiga shared-library functions. [Original GPL compiler](https://strlen.com/amiga-e/). |  |
| [AmiBlitz3 (Amiga BASIC dialect)](https://github.com/AmiBlitz/AmiBlitz3/blob/master/Docs/Amiblitz3.guide) | Native (68k) | Desktop (Amiga) | The toolchain generates wrappers for calling native functions in Amiga shared libraries. [Compiler source and licenses](https://github.com/AmiBlitz/AmiBlitz3). |  |
| [Monkey 2](https://github.com/nitrologic/monkey2/wiki/NativeCode) | Native (via C++) | Desktop | The `Extern` directive lets Monkey 2 code call native functions on supported targets. [Zlib-licensed toolchain](https://github.com/blitz-research/monkey2). |  |
| [BlitzPlus](https://github.com/blitz-research/blitzplus_msvc2017) | Native (Blitz BASIC compiler) | Desktop (Windows) | Userlibs expose native DLL functions to BlitzPlus programs. [OpenGL userlib example](https://github.com/AlexCeleste/bOGL-2). zlib/libpng. |  |
| [Gerbil Scheme (Gambit backend)](https://cons.io/guide/ffi.html) | Native (via Gambit and C) | Desktop | Gerbil uses Gambit's C interface to write bindings to native libraries. [Source and licensing](https://github.com/mighty-gerbils/gerbil). |  |
| [Clasp (Common Lisp)](https://github.com/clasp-developers/clasp) | Native (LLVM) | Desktop | Exposes native C++ libraries and functions to Common Lisp through its C++ interop layer. |  |
| [CMUCL (Common Lisp)](https://cmucl.org/docs/cmu-user/cmu-user.html) | Native | Desktop | The `alien` interface can call native functions using the C calling convention. [Source and licensing overview](https://cmucl.org/). |  |
| [Bigloo (Scheme)](https://www-sop.inria.fr/indes/fp/Bigloo/manual-chapter27.html) | Native (C backend) | Desktop | Its C interface imports native functions and variables and can export Scheme functions to C. [Source and license](https://github.com/manuel-serrano/bigloo). |  |
| [GOAL (OpenGOAL)](https://opengoal.dev/docs/porting-info/porting_to_x86/) | Native (OpenGOAL compiler) | Desktop | The ISC-licensed implementation compiles GOAL to x86-64 and integrates with a C++ game runtime. [Source and license](https://github.com/open-goal/jak-project). |  |
| [CubeScript (Red Eclipse)](https://github.com/redeclipse/oldwiki/blob/master/CubeScript.md) | Game scripting (Cube 2 family) | Desktop (game engine) | Scripts call commands registered in C++ by the game engine. [Red Eclipse implementation](https://github.com/redeclipse/base/blob/master/src/engine/command.cpp) and [source license](https://github.com/redeclipse/base/blob/master/doc/license.txt). |  |
| [MWScript (OpenMW)](https://github.com/OpenMW/openmw/blob/master/apps/openmw/mwscript/dialogueextensions.hpp) | Game scripting (OpenMW interpreter) | Desktop (OpenMW) | Gameplay scripts call built-in operations implemented by the native C++ engine. [GPLv3 engine](https://github.com/OpenMW/openmw). |  |
| [NWScript (xoreos)](https://github.com/xoreos/xoreos/blob/master/NEWS.md) | Managed VM (game engine) | Desktop (xoreos) | Game scripts call engine functions implemented in native C++ by the [GPLv3-or-later xoreos engine](https://github.com/xoreos/xoreos). |  |
| [EDL (ENIGMA Development Language)](https://enigma-dev.org/docs/Wiki/Tutorial%3AMoving_from_GML_to_EDL) | Native (via C++) | Desktop | The GML-compatible language supports [native library calls](https://enigma-dev.org/forums/index.php?topic=3065.0) through `external_define` and `external_call`. [Open-source ENIGMA toolchain](https://enigma-dev.org/docs/Wiki/License). |  |
| [Otus Lisp](https://github.com/yuriy-chumak/ol) | Managed VM (Ol) | Desktop | This Scheme dialect includes a built-in C FFI and OpenGL bindings. MIT or LGPLv3-or-later. |  |
| [Tengo](https://github.com/d5/tengo) | Managed VM (embedded) | Desktop (inside a Go host) | Go hosts can expose callable native functions as Tengo objects. |  |
| [Anko](https://github.com/mattn/anko/) | Interpreted (embedded) | Desktop (inside a Go host) | An embedded scripting environment can expose compiled Go functions and values to scripts. |  |
| [PocketLang](https://github.com/ThakeeNathees/pocketlang/blob/master/src/include/pocketlang.h) | Interpreted (embedded VM) | Desktop (inside a host application) | A C host can register native functions that scripts can call through the embedding API. [MIT](https://github.com/ThakeeNathees/pocketlang). |  |
| [Gravity](https://github.com/marcobambini/gravity) | Interpreted (embedded VM) | Desktop (inside a host application) | Embeddable scripting language with native host integration and an Objective-C bridge. MIT. |  |
| [Pluto](https://github.com/PlutoLang/Pluto) | Managed VM (Lua family) | Desktop | A Lua superset that can reach native code through Lua-style C modules. |  |
| [HamsterSpeak](https://rpg.hamsterrepublic.com/ohrrpgce/HamsterSpeak_Specification) | Game scripting (OHRRPGCE) | Desktop (OHRRPGCE) | Scripts call built-in commands implemented by the RPG engine. [Engine source and licenses](https://github.com/ohrrpgce/ohrrpgce). |  |
| [Coconut](https://coconut-lang.org/) | CPython runtime | Desktop | Compiles its functional syntax to Python and can call native libraries through Python's [ctypes](https://docs.python.org/3/library/ctypes.html). Apache 2.0. |  |
| [LiveScript](https://livescript.net/) | JS engine | Web | Compiles to JavaScript; browser APIs and JS interop provide a route to WebGL. [MIT](https://github.com/gkz/LiveScript). |  |
| [Ur/Web](https://github.com/urweb/urweb) | Native (server), JS engine (client) | Server, Web | Its FFI links server-side C and maps client-side calls to JavaScript wrappers, which can call WebGL. [FFI manual](https://github.com/urweb/urweb/blob/master/doc/manual.tex). |  |
| [Links](https://github.com/links-lang/links/wiki/JavaScript-FFI) | Interpreted (server), JS engine (client) | Server, Web | The `alien javascript` interface exposes browser-side JavaScript functions, including wrappers around WebGL. [Project source](https://github.com/links-lang/links). |  |
| [Opa](https://opalang.org/) | JS engine | Server, Web | Compiles client-side code to JavaScript and supports JavaScript libraries for browser integration. [AGPL compiler and MIT library](https://github.com/mlstate/opalang). |  |
| [WurstScript](https://wurstlang.org/features/backends.html) | Game scripting (Jass or Lua backend) | Inside Warcraft III | The open-source compiler targets scripting runtimes that call game engine natives. [Compiler source](https://github.com/wurstscript/WurstScript). |  |
| [Parenscript](https://parenscript.common-lisp.dev/reference.html) | JS engine | Web | Compiles Lisp-style syntax to JavaScript. Browser programs can call WebGL methods through JavaScript interop. [BSD-3-Clause](https://parenscript.common-lisp.dev/). |  |
| [DWScript](https://compendium.dwscript.net/lang/tooling/js_transpiler) | Interpreted or JS engine | Desktop, Web | Delphi-like scripting language with a JavaScript backend that can wrap browser APIs through JavaScript integrations. [Source](https://bitbucket.org/egrange/dwscript/). |  |
| [Component Pascal (BlackBox)](https://github.com/BlackBoxCenter/blackbox) | Native | Desktop | BlackBox interface modules map Component Pascal procedure declarations to native DLL functions. [BlackBox documentation](https://oberoncore.ru/_media/library/bb_class.pdf). BSD-2-Clause. |  |
| [eC](https://github.com/ecere/ecere-sdk) | Native (via C) | Desktop | The Ecere SDK provides GUI and OpenGL integration. The BSD-licensed [eC toolchain](https://github.com/ecere/eC) compiles through C. |  |
| [BaCon](https://www.basic-converter.org/documentation.html) | Native (via C) | Desktop | This BASIC dialect uses `IMPORT` to call functions from native libraries. Its HUG library supports several GUI toolkits. [MIT](https://chiselapp.com/user/bacon/repository/bacon/home). |  |
| [QBJS](https://github.com/boxgaming/qbjs/wiki/QBasic-Language-Support) | JS engine | Web | A QBasic dialect for the browser. Its inline JavaScript blocks can call browser APIs including WebGL. [MIT](https://github.com/boxgaming/qbjs). |  |
| [Red/System](https://static.red-lang.org/red-system-specs.html) | Native | Desktop | `#import` imports functions from native shared libraries. |  |
| [ACE BASIC (Amiga)](https://github.com/mdbergmann/ACEBasic) | Native (68k) | Desktop (Amiga) | Supports calling AmigaDOS and other shared-library functions, including graphics and windowing APIs. The compiler source is available under the GPL. |  |
| [Amber Smalltalk](https://github.com/amber-smalltalk/amber) | JS engine | Web | Browser-oriented Smalltalk implementation that compiles to JavaScript. Browser WebGL can be reached through JavaScript wrappers. MIT. |  |
| [ArkScript](https://arkscript-lang.dev/docs/tutorials/modules/) | Managed VM (embedded) | Desktop | Native C++ modules expose functions to scripts. [Source and license](https://github.com/ArkScript-lang/Ark). |  |
| [Austral](https://austral-lang.org/tutorial/) | Native (via C) | Desktop | Its C interface lets foreign function declarations bind to linkable C functions. [Apache 2.0 with LLVM exception](https://github.com/austral/austral). |  |
| [Basilisp](https://github.com/basilisp-lang/basilisp) | CPython runtime | Desktop | A Clojure-style Lisp whose Python interoperability lets scripts use [ctypes](https://docs.python.org/3/library/ctypes.html) for native calls. EPL 1.0. |  |
| [Boron](https://urlan.sourceforge.io/boron/) | Interpreted (embedded) | Desktop (inside a C host) | Rebol-inspired language that can be embedded and extended through its C library. |  |
| [C2](https://github.com/c2lang/c2compiler) | Native | Desktop | A C-family language with [bindings for libc and other native libraries](https://github.com/c2lang/c2_libs). Apache 2.0. |  |
| [CaffeineScript](https://github.com/caffeine-suite/caffeine-script) | JS engine | Web | Compiles to JavaScript. Browser builds can reach WebGL through JavaScript calls. [ISC](https://github.com/caffeine-suite/caffeine-script/blob/master/package.json). |  |
| [Cakelisp](https://github.com/jduhamel/cakelisp) | Native (via C/C++) | Desktop | A game-oriented compiled language with direct access to C and C++ declarations and functions. The project links its upstream source repository. |  |
| [Cicada](https://cicadascript.sourceforge.io/) | Interpreted (embedded) | Desktop | C-like scripting language that can be linked with C code and call host functions. |  |
| [Citrine](https://citrine-lang.org/faq.ctr) | Interpreted | Desktop | Loads compiled C plugins as shared libraries. Its Media plugin uses SDL for apps and games. [BSD source license](https://citrine-lang.org/credits.ctr). |  |
| [Clay](https://github.com/jckarter/clay/blob/master/doc/language-reference.md#external-functions) | Native | Desktop | The `external` declaration calls C functions and can export Clay functions with C linkage. [Project and license](https://github.com/jckarter/clay). |  |
| [Clue](https://github.com/ClueLang/Clue) | Lua runtime | Desktop | Compiles to Lua and can use [Lua's native module system](https://www.lua.org/manual/5.4/manual.html#pdf-package.loadlib). MIT. |  |
| [Corman Lisp (Common Lisp)](https://github.com/sharplispers/cormanlisp/blob/master/documentation/parse.html) | Native | Desktop (Windows) | The C header parser translates declarations for binding native DLLs. [MIT source distribution](https://github.com/sharplispers/cormanlisp). |  |
| [Dyon](https://github.com/PistonDevelopers/dyon) | Interpreted (embedded) | Desktop (inside a Rust host) | A Rust application can expose functions to Dyon scripts through the embedding API. |  |
| [Earl Grey](https://github.com/breuleux/earl-grey) | JS engine | Web | Compiles to JavaScript. Browser WebGL can be reached through JavaScript interop. MIT. |  |
| [EndBASIC](https://jmmv.dev/2021/01/embedding-endbasic.html) | Managed VM (embedded) | Desktop, Web | Rust applications can register commands that BASIC programs call. [Runtime and license](https://github.com/endbasic/endbasic). |  |
| [fe](https://github.com/rxi/fe) | Interpreted (embedded) | Desktop (inside a C host) | Small Lisp-like language that can call C functions registered by its host. |  |
| [Felix](https://github.com/felix-lang/felix) | Native (compiles through C++) | Desktop | Bindings integrate existing C and C++ libraries. |  |
| [Feral](https://github.com/Feral-Lang/Feral) | Managed VM (embedded) | Desktop | An MIT-licensed scripting language that can use native modules. [LibPG](https://github.com/Feral-Lang/LibPG) exposes PostgreSQL's C library. |  |
| [GameMonkey Script](https://github.com/publicrepo/gmscript) | Managed VM (embedded) | Desktop (inside a C++ host) | Game-oriented scripting language with bindings registered by its C++ host. MIT. |  |
| [Genie](https://docs.vala.dev/genie/introduction/01-compiling.html) | Native (via C) | Desktop | Uses Vala’s compiler and VAPI bindings to call C libraries. The [Genie guide](https://docs.vala.dev/genie/) includes GTK examples. |  |
| [Glojure (Clojure on Go)](https://github.com/glojurelang/glojure) | Interpreted or ahead-of-time compiled through Go | Desktop, Server | The Go interop package registry exposes compiled Go functions, types, and values to Glojure programs. EPL 1.0. |  |
| [Goby](https://github.com/goby-lang/goby) | Managed VM (Go host) | Desktop (Linux and macOS for dynamic plugins) | The interpreter can load compiled Go packages through its plugin system. MIT. |  |
| [GorillaScript](https://github.com/ckknight/gorillascript) | JS engine | Web | Compiles to JavaScript. Browser graphics APIs such as WebGL can be accessed through JavaScript calls. MIT. |  |
| [Hop (Scheme client compiler)](https://www-sop.inria.fr/members/Manuel.Serrano/publi/ls-tfp07.pdf) | Scheme runtime (server) and JS engine (client) | Server, Web | Hop compiles client code to browser JavaScript, where JavaScript wrappers can expose WebGL. [Toolchain and license](https://github.com/manuel-serrano/hop). |  |
| [Icon (University of Arizona implementation)](https://raptor.cs.arizona.edu/icon/uguide/cfuncs.htm) | Interpreted (Icon VM) | Desktop | The University of Arizona implementation loads C functions from shared libraries on supported systems. Icon is the predecessor of Unicon. [Project and source](https://raptor.cs.arizona.edu/icon/). |  |
| [Jac (native codespace)](https://github.com/jaseci-labs/jac/blob/main/jac/jaclang/cli/docs/reference/import-anything.md) | Native, CPython runtime, or JS engine | Desktop, Web | Jac's native codespace can call functions from C ABI shared libraries. [MIT compiler](https://github.com/jaseci-labs/jac). |  |
| [Jancy](https://vovkos.github.io/jancy/api/) | JIT (LLVM) | Desktop | Embeddable scripting language with C ABI interoperability and a C API. [Source](https://github.com/vovkos/jancy). |  |
| [Jinx](https://github.com/JamesBoer/Jinx) | Managed VM (embedded) | Desktop (inside a C++ host) | Designed for games and real-time applications. Scripts can call native functions registered through the C++ API. |  |
| [JStar](https://github.com/bamless/jstar) | Managed VM (embedded) | Desktop (inside a C host) | Offers a C API for native integration. A host can supply game or application APIs. MIT. |  |
| [JSX (jsx/JSX)](https://github.com/jsx/JSX) | JS engine | Web | A typed language that compiles to JavaScript and can use WebGL through browser bindings. [WebGL example](https://gist.github.com/Santarh/2890489). MIT. |  |
| [Kaffeine](https://github.com/weepy/kaffeine) | JS engine | Web | Compiles its extended syntax to JavaScript. Browser builds can call WebGL through JavaScript APIs. MIT. |  |
| [Ketos](https://github.com/murarth/ketos) | Interpreted (embedded) | Desktop (inside a Rust host) | Lisp dialect for Rust programs. Hosts can expose Rust functions as foreign functions. |  |
| [Liberty Eiffel](https://wiki.liberty-eiffel.org/index.php/Wrapping_C_libraries) | Native (via C) | Desktop | Eiffel external routines can wrap compiled C libraries. [GNU Eiffel toolchain](https://www.liberty-eiffel.org/). |  |
| [LIL](https://runtimeterror.com/tech/lil/) | Interpreted (embedded) | Desktop | The Little Interpreted Language supports command callbacks registered by its C or Pascal host. |  |
| [Lily](https://lily-lang.org/tooling-bindgen.html) | Interpreted (embedded) | Desktop | Bindgen generates bindings for extending Lily with C functions. [Source](https://github.com/fascinatedbox/lily). |  |
| [LispyScript](https://github.com/santoshrajan/lispyscript) | JS engine | Web | A Lisp-style language that compiles to JavaScript and can reach browser WebGL through JavaScript calls. |  |
| [Lissp (Hissp)](https://hissp.readthedocs.io/en/latest/) | CPython runtime | Desktop | The Lissp frontend compiles through Hissp to Python. It can use Python's [ctypes](https://docs.python.org/3/library/ctypes.html) to call shared-library functions. [Compiler license](https://github.com/gilch/hissp). |  |
| [Lobster](https://github.com/aardappel/lobster/blob/master/docs/source/implementation.md) | JIT or native via C++ | Desktop, Web/WASM (via Emscripten) | Game-oriented language that exposes C++ functions as built-ins. Uses OpenGL and SDL on desktop. Apache 2.0. |  |
| [LoLa](https://github.com/ikskuh/LoLa) | Managed VM (embedded) | Desktop (inside a Zig or C++ host) | Game scripts can call synchronous and asynchronous functions registered by the native host. MIT. |  |
| [Lumen (Lisp)](https://github.com/sctb/lumen) | JS engine or Lua runtime | Web, Desktop | Compiles to JavaScript or Lua. Browser builds can use JavaScript wrappers to call WebGL. BSD-2-Clause. |  |
| [Mint](https://api.mint-lang.com/guide/reference/javascript-interop/inlining) | JS engine | Web | Inline JavaScript can wrap browser WebGL APIs and JavaScript graphics libraries. [BSD-3-Clause compiler](https://github.com/mint-lang/mint). |  |
| [Move (JavaScript-derived language)](https://github.com/rsms/move) | JS engine | Web | Compiles to browser JavaScript and can call WebGL through JavaScript host objects. MIT. |  |
| [MY-BASIC](https://github.com/paladin-t/my_basic) | Interpreted (embedded) | Desktop (inside a C host) | The interpreter API lets hosts register native functions that BASIC code can call. |  |
| [Nu](https://github.com/programming-nu/nu) | Interpreted | Desktop (macOS, Linux) | Lisp-style syntax sends messages to native Objective-C objects through the Objective-C runtime. Apache 2.0. |  |
| [Oberon-07 (OberonJS)](https://github.com/vladfolts/oberonjs) | JS engine | Web | The OberonJS compiler emits browser JavaScript. JavaScript wrappers can expose browser APIs, including WebGL. MIT. |  |
| [ooc](https://ooc-lang.github.io/docs/lang/covers/) | Native (compiles through C99) | Desktop | `cover` declarations map C types and can expose external functions. [Compiler repository](https://github.com/ooc-lang/rock). |  |
| [Phix](https://phix.x10.mx/docs/html/ccall.htm) | Native | Desktop | Calls C shared libraries and provides [OpenGL bindings](https://www.phix.x10.mx/docs/html/opengl_1.1.htm). [OSL 3.0 compiler and AFL 3.0 for most runtime and library components](https://phix.x10.mx/docs/html/licence.htm). |  |
| [PogoScript](https://featurist.github.io/pogoscript/guide/) | JS engine | Web | Compiles to JavaScript with ordinary JavaScript objects and calls, so browser builds can use WebGL APIs. [BSD-2-Clause compiler](https://github.com/featurist/pogoscript). |  |
| [RapydScript-NG](https://github.com/kovidgoyal/rapydscript-ng) | JS engine | Web | Its Python-like syntax compiles to JavaScript and can call browser WebGL APIs through JavaScript interop. BSD-2-Clause. |  |
| [Roy](https://github.com/puffnfresh/roy) | JS engine | Web | Compiles to JavaScript and can call JavaScript host APIs, including browser WebGL methods. MIT. |  |
| [Sather (GNU Sather)](https://steveomohundro.com/wp-content/uploads/2009/03/stoutamire_omohundro95_sather_11.pdf) | Native (via C) | Desktop | `external C class` declares interfaces to ANSI C functions. [GNU Sather compiler](https://www.gnu.org/software/sather/). |  |
| [Scheme 48](https://www.s48.org/1.9.1/manual/manual-Z-H-9.html) | Managed VM | Desktop | Its external modules and dynamic-loading interface expose C functions to Scheme. [Source license and reference](https://www.s48.org/1.9.1/s48manual.pdf). |  |
| [Seed7](https://seed7.net/manual/ffi.htm) | Interpreted; Native via C compiler | Desktop | C interoperability through wrapper functions and its foreign-function interface. [GPL; runtime LGPL](https://seed7.net/faq.htm#Which_license_does_Seed7_use). |  |
| [SGScript](https://www.sgscript.org/pages/docpages/sgscript.docs.htm) | Managed VM (embedded) | Desktop | The scripting engine's C API exposes native functions and objects. [Source](https://github.com/archo5/sgscript). |  |
| [Sibilant](https://github.com/jbr/sibilant) | JS engine | Web | A Lisp-style language that compiles to JavaScript and can call WebGL through the JavaScript host. MIT. |  |
| [SquiLu](https://github.com/mingodad/squilu) | Managed VM (Squirrel-derived) | Desktop | Provides native extensions and GUI bindings for FLTK. Includes a template for adding modules. |  |
| [StratifiedJS (Oni StratifiedJS)](https://github.com/onilabs/stratifiedjs) | JS engine | Web | The MIT-licensed browser runtime runs StratifiedJS in the page, where code can call JavaScript APIs such as WebGL. |  |
| [Turing (OpenTuring)](https://github.com/Open-Turing-Project/OpenTuring) | Interpreted | Desktop (Windows) | The interpreter can register compiled C routines that programs call through `external` declarations. [OpenTuring license](https://raw.githubusercontent.com/Open-Turing-Project/OpenTuring/master/LICENSE.txt). |  |
| [Volt](https://github.com/VoltLang/Amp) | Native (LLVM) | Desktop | Amp provides bindings to C libraries including SDL2, SDL2_image, and SDL2_ttf. [Compiler and license](https://github.com/VoltLang/Volta). |  |
| [XBLite](https://xblite.org/whats-new/) | Native | Desktop (Windows) | `EXTERNAL CFUNCTION` declares native routines. XBLite is a [GPL-licensed XBasic dialect](https://xblite.org/) oriented toward Windows application development. |  |
| [YueScript (Dora SSR)](https://dora-ssr.net/docs/tutorial/language-select/) | Lua runtime | Desktop (via Dora SSR) | Compiles to Lua and can call the native APIs exposed by the Dora SSR game engine. [MIT compiler](https://github.com/IppClub/YueScript). |  |
| [Zen C](https://github.com/Zuhaitz-dev/zenc-lang) | Native (via C) | Desktop | Compiles to C and interoperates with existing native libraries. The project also documents Objective-C integration. |  |
| [ZIL (ZILF Glulx target)](https://github.com/taradinoc/zilf/blob/branch/default/CHANGELOG.md) | Interactive-fiction VM (Glulx) | Desktop | ZILF's Glulx target provides a `GLK` built-in that calls the host interpreter's Glk functions. [Compiler license](https://github.com/taradinoc/zilf). |  |
| [ZLang](https://github.com/zlangdevs/zlang) | Native (LLVM) | Desktop | Supports direct C interoperability. The project links raylib bindings and a raylib-based game example. |  |
| [Cone](https://cone.jondgoodwin.com/coneref/refinclude.html) | Native (LLVM) | Desktop | An experimental systems language. `extern` declares C-compatible functions. [Compiler and license](https://github.com/jondgoodwin/cone). |  |
| [FuelLang](https://github.com/awemorris/fuel) | Game scripting (Fuel engine) | Desktop (Fuel engine) | The MIT-licensed Fuel engine provides hardware-accelerated rendering and a built-in language for writing games. |  |
| [Fun](https://github.com/omdxp/fun) | Native (via C) | Desktop | Compiles to C and provides signatures for C interoperability. MIT. |  |
| [G# (David Obando)](https://github.com/DavidObando/gsharp) | Managed VM (.NET CLR) | Desktop | `@DllImport` creates P/Invoke bindings for calls to native libraries. MIT. |  |
| [Ganymede](https://github.com/tinyBigGAMES/Ganymede) | Native (x64 JIT) | Desktop (Windows) | Scripts call native DLL functions through `external` declarations or use functions registered by the host. Apache 2.0. |  |
| [Gossamer](https://github.com/gossamer-lang/gossamer/blob/main/SPEC.md) | Native (LLVM) or bytecode VM | Desktop | `unsafe extern "C"` declares functions from libc and other native libraries. [Apache 2.0](https://github.com/gossamer-lang/gossamer). |  |
| [Hike](https://github.com/kanryu/hike-lang) | Native (LLVM), WASM | Desktop, Web/WASM | Supports direct C ABI interoperability on native targets. The compiler is MIT-licensed. |  |
| [Ignis](https://github.com/Ignis-lang/ignis) | Native (via C) | Desktop | C interop is provided through `extern` blocks. GPLv3 compiler. |  |
| [Ingle](https://github.com/ingle-lang/ingle) | Native | Desktop | `extern "c"` calls C functions. The project provides an opt-in immediate-mode graphics module. MIT. |  |
| [j2](https://github.com/jasonnyberg/j2) | Native-capable concatenative language | Desktop | Supports calling C libraries using their native ABI and types. [Project licenses](https://github.com/jasonnyberg/j2). |  |
| [JHYY](https://github.com/JiHuiYiYou/JiHuiYiYou-compiler) | Native (x86-64) | Desktop | Uses the C ABI for foreign calls and generates native Windows and Linux binaries. MIT. |  |
| [JustLang](https://github.com/tbit-bdd/JustLang) | Interpreted (embedded) | Desktop | Loads compiled C plugins that register native functions callable from scripts. MIT. |  |
| [Jux](https://github.com/xdsswar/juxlang) | Native (via Rust) | Desktop | Experimental compiler with C and C++ FFI. Apache 2.0. |  |
| [Kairo](https://kairolang.org/docs/language/c-cpp/) | Native | Desktop | Supports C and C++ interoperability. [Apache 2.0 compiler with a runtime library exception](https://github.com/kairolang/kairo). |  |
| [Kiln](https://github.com/AxDSan/kiln) | Native | Desktop | `[Dll(...)]` binds native functions. The compiler also supports native GUI applications. MIT or BSD-3-Clause. |  |
| [LiteScript (JavaScript backend)](https://github.com/luciotato/LiteScript) | JS engine | Web | Compiles for browser execution and can use JavaScript browser APIs, including WebGL. AGPLv3. |  |
| [Lykn](https://github.com/lykn-lang/lykn) | JS engine | Web | The compiler emits browser JavaScript and exposes JavaScript interop through its kernel forms, allowing calls to browser WebGL APIs. Apache 2.0. |  |
| [Magnesium](https://github.com/QNSET-Software-Development/magnesium) | Managed VM (embedded) | Desktop (inside a host) | A host can register native functions or use the runtime’s numeric C function-pointer FFI. MIT. |  |
| [MetaScript](https://github.com/metascript/metascript) | JS engine | Web | Compiles to JavaScript. Browser WebGL can be reached through JavaScript calls in the generated program. MIT. |  |
| [Mimas](https://github.com/imlazyeye/mimas) | Managed VM (embedded) | Desktop (inside a Rust host) | Statically typed scripting for Rust hosts. The Bevy Breakout example exposes engine components and resources to scripts. MIT or Apache 2.0. |  |
| [Mond](https://github.com/Rohansi/Mond/wiki/Automatic-Binding) | Managed VM (embedded in .NET) | Desktop (inside a .NET host) | The binding API exposes C# functions and classes to scripts. Native APIs can be exposed through the host. [Source](https://github.com/Rohansi/Mond). |  |
| [Myra](https://github.com/tinyBigGAMES/MyraLang) | Native (via C++23) | Desktop | Allows inline C++ in Myra source, including calls to native functions. Apache 2.0. |  |
| [NanoLang](https://github.com/jordanhubbard/nanolang/blob/main/MEMORY.md) | Native (via C) or bytecode VM | Desktop | Native modules expose C functions through `extern fn`. The VM also offers a separate-process FFI mode. [Apache 2.0 compiler](https://github.com/jordanhubbard/nanolang). |  |
| [Nari](https://github.com/nari-lang/nari) | JIT | Desktop | Provides an FFI for calling C functions. GPLv3. |  |
| [Neat](https://neat-lang.github.io/manual.html) | Native | Desktop | Supports `extern(C)` declarations and a `cimport` macro for existing C APIs. [BSD-3-Clause](https://github.com/Neat-Lang/neat). |  |
| [ReCT](https://github.com/RedCubeDev-ByteSpace/ReCT) | Managed VM (.NET) | Desktop | The [raylib bindings index](https://github.com/raysan5/raylib/blob/master/BINDINGS.md) lists Relib for raylib 3.5. The binding repository could not be independently verified. GPLv3 compiler. |  |
| [Sage (Adam McDaniel)](https://github.com/adam-mcdaniel/sage) | Native or managed VM | Desktop, Web | The FFI can call C functions or backend-specific host functions. The browser demo uses JavaScript callbacks for graphics. MIT. |  |
| [SOPL](https://github.com/Dcraftbg/SOPL) | Native | Desktop | Its `extern` declarations link calls to native functions, including functions in libc. MIT. |  |
| [Teko](https://github.com/teko-org/teko-lang) | Native (AOT) | Desktop | The self-hosting compiler supports C FFI. Apache 2.0 or MIT. |  |
| [tiko](https://github.com/PaulSquires/tiko-lang) | Native | Desktop | Supports importing C headers for native interoperability and ships graphics libraries. GPLv2-or-later compiler. |  |
| [Toy](https://github.com/krgamestudios/Toy) | Interpreted (embedded) | Desktop (inside a C host) | Embedded scripts can call native C bindings. zlib license. |  |
| [Vox](https://github.com/MrSmith33/vox/blob/master/internals.md) | Native (AOT or JIT) | Desktop | Uses the C calling convention and can bind host function pointers to external declarations. [Boost Software License](https://github.com/MrSmith33/vox). |  |
| [White](https://github.com/whitelanguage/white) | Native | Desktop | `extern` declarations bind C ABI functions from native libraries. Apache 2.0. |  |
| [ZetScript](https://zetscript.org/) | Managed VM (embedded) | Desktop (inside a C++ host) | C++ applications can register native functions and types for scripts to use. |  |
| **Non-UI-centric languages** | | | | |
| [PostgreSQL (SQL)](https://www.postgresql.org/docs/18/xfunc-c.html) | Compiled queries (PostgreSQL server) | Server | `CREATE FUNCTION ... LANGUAGE C` registers a function from a compiled shared library so it can be called from SQL. [PostgreSQL License](https://www.postgresql.org/about/licence/). |  |
| [Bash](https://www.gnu.org/software/bash/manual/bash.html#Bash-Builtins) | Interpreted shell | Desktop (CLI) | `enable -f` loads compiled shared objects that provide callable builtins on systems with dynamic loading. GNU GPL. |  |
| [SQLite (SQL)](https://www.sqlite.org/loadext.html) | Compiled queries (SQLite engine) | Desktop, Server | Native C extensions register functions that SQLite queries can call. [SQLite public-domain terms](https://www.sqlite.org/copyright.html). |  |
| [MySQL Community Server (SQL)](https://dev.mysql.com/doc/refman/8.4/en/create-function-loadable.html) | Compiled queries (MySQL server) | Server | `CREATE FUNCTION … SONAME` registers a compiled shared-library function for calls from SQL. [Open-source Community Edition](https://dev.mysql.com/downloads/mysql/). |  |
| [PowerShell](https://learn.microsoft.com/en-us/powershell/module/microsoft.powershell.utility/add-type?view=powershell-7.5) | Managed shell (.NET) | Desktop (CLI) | `Add-Type` compiles managed wrappers that can call native libraries through P/Invoke. [MIT](https://github.com/PowerShell/PowerShell/blob/master/LICENSE.txt). |  |
| [MariaDB (SQL)](https://mariadb.com/docs/server/reference/sql-statements/data-definition/create/create-function) | Compiled queries (MariaDB server) | Server | The loadable form of `CREATE FUNCTION` makes native functions from shared libraries callable in SQL. [GPLv2 server](https://github.com/MariaDB/server). |  |
| [Zsh](https://zsh.sourceforge.io/Doc/Release/Zsh-Modules.html) | Interpreted shell | Desktop (CLI) | `zmodload` loads native modules that can expose callable shell builtins and math functions. [Source and license](https://github.com/zsh-users/zsh). |  |
| [GNU Make (Makefile language)](https://www.gnu.org/software/make/manual/make.html#Loading-Objects) | Build language (GNU make) | Desktop (CLI) | The `load` directive loads a native shared object, which can register functions callable from a Makefile via `gmk_add_function`. |  |
| [GNU Awk (AWK)](https://www.gnu.org/software/gawk/manual/gawk.html#Dynamic-Extensions) | Interpreted | Desktop (CLI) | `@load` makes functions from compiled C extensions callable from scripts. |  |
| [Nix (NixOS evaluator)](https://nix.dev/manual/nix/2.34/command-ref/conf-file#conf-allow-unsafe-native-code-during-evaluation) | Interpreted configuration language | Desktop (Linux and macOS; Windows via WSL 2), Server | With native evaluation enabled, `builtins.importNative` loads a shared library and invokes its exported entry point. [Source and license](https://github.com/NixOS/nix/blob/master/COPYING). |  |
| [DuckDB (SQL)](https://duckdb.org/2025/02/05/announcing-duckdb-120) | Compiled queries (DuckDB engine) | Desktop, Server | Native extensions can register scalar, aggregate, and table functions through the C API so SQL queries can call them. [MIT engine](https://github.com/duckdb/duckdb). |  |
| [Emacs Lisp](https://www.gnu.org/software/emacs/manual/html_node/elisp/Loading.html) | Emacs runtime | Desktop (inside Emacs) | The GNU Emacs implementation can load native dynamic modules that expose callable Lisp functions. It is mainly used to extend the editor. |  |
| [Vim script (Vim)](https://vimhelp.org/builtin.txt.html#libcall%28%29) | Interpreted (Vim) | Desktop (inside Vim) | In Vim builds with `+libcall`, `libcall()` and `libcallnr()` call functions in shared libraries. Primarily used for editor scripting. [Vim source and license](https://github.com/vim/vim). |  |
| [HLSL (glslang and SPIRV-Cross)](https://github.com/KhronosGroup/SPIRV-Cross/blob/main/README.md#hlsl-source-to-glsl) | GPU shader language | Web (WebGL 2 for compatible shaders) | The open-source toolchain converts HLSL through SPIR-V to GLSL ES. WebGL 2 requires GLSL ES 3.00 and its supported feature subset. [glslang compiler](https://github.com/KhronosGroup/glslang). |  |
| [GLSL ES](https://registry.khronos.org/webgl/specs/latest/1.0/) | GPU shader language | Web (WebGL) | Runs WebGL vertex and fragment shaders. The host application handles input, game state, and draw calls. |  |
| [LLVM IR](https://llvm.org/docs/LangRef.html) | Compiler intermediate language | Desktop, Server | `declare` and `call` express external calls using the target's C calling convention. [LLVM source and license](https://github.com/llvm/llvm-project). |  |
| [SWI-Prolog](https://www.swi-prolog.org/pldoc/man?section=foreign) | Managed VM (SWI-Prolog) | Desktop | SWI-Prolog provides a C foreign-language interface. [SWI-Prolog: BSD-2-Clause](https://www.swi-prolog.org/license.html). |  |
| [GNU Octave](https://docs.octave.org/latest/Calling-External-Code-from-Oct_002dFiles.html) | Interpreted | Desktop | Native `.oct` extensions integrate C, C++, and Fortran routines. [GPL](https://octave.org/about). |  |
| [Starlark (Starlark-Go)](https://pkg.go.dev/go.starlark.net/starlark#NewBuiltin) | Interpreted (embedded) | Desktop (inside a Go host) | The Go implementation lets hosts expose native functions as built-ins for configuration or mod scripting. [Source repository](https://github.com/google/starlark-go). |  |
| [Rego (Open Policy Agent)](https://www.openpolicyagent.org/docs/extensions) | Policy language (Open Policy Agent) | Desktop, Server | A Go host embedding OPA can add native built-ins callable from policies. [Apache 2.0](https://github.com/open-policy-agent/opa/blob/main/LICENSE). |  |
| [Jsonnet (libjsonnet)](https://jsonnet.org/ref/bindings.html) | Interpreted (embedded) | Desktop (inside a host) | Data templating language. Hosts can register native callbacks through the [C API](https://github.com/google/jsonnet/blob/master/include/libjsonnet.h). |  |
| [Triton](https://triton-lang.org/main/getting-started/tutorials/07-extern-functions.html) | Compiled GPU kernel DSL | GPU | Kernels can call external device-library functions, such as CUDA `libdevice` functions. [Source and license](https://github.com/triton-lang/triton/blob/main/LICENSE). |  |
| [SystemVerilog](https://verilator.org/guide/latest/connecting.html) | Hardware description and verification | Simulation (Verilator) | `import "DPI-C"` makes native C or C++ functions callable during simulation. [Simulator license](https://github.com/verilator/verilator/blob/master/LICENSE). |  |
| [Common Expression Language (CEL-Go)](https://github.com/cel-expr/cel-go/blob/master/examples/example_cel_custom_functions_test.go) | Expression language (embedded) | Inside a host | CEL-Go lets an application register compiled Go functions as callable overloads. [Source and license](https://github.com/cel-expr/cel-go). |  |
| [VHDL (GHDL)](https://ghdl.github.io/ghdl-cosim/vhpidirect/declarations.html) | Hardware description | Simulation (GHDL) | GHDL's `VHPIDIRECT` interface binds VHDL subprograms to native code. [Source and license](https://github.com/ghdl/ghdl). |  |
| [WebAssembly text format (WAT)](https://developer.mozilla.org/en-US/docs/WebAssembly/Guides/Understanding_the_text_format) | WASM engine | Web/WASM | Handwritten modules can import JavaScript functions that wrap WebGL calls. The browser host owns the graphics context. |  |
| [WGSL (wgpu WebGL2 backend)](https://github.com/gfx-rs/wgpu) | GPU shader language | Web (WebGL 2) | The WebGL2 backend lowers compatible WGSL shaders to GLSL ES through Naga. [wgpu and Naga source and licenses](https://github.com/gfx-rs/wgpu). |  |
| [MLIR (LLVM ExecutionEngine)](https://mlir.llvm.org/doxygen/classmlir_1_1ExecutionEngine.html) | Compiler intermediate language | Desktop, Server | The execution engine can resolve external calls from JIT-compiled MLIR against registered native symbols. [LLVM project license](https://github.com/llvm/llvm-project). |  |
| [Godot Shading Language](https://docs.godotengine.org/en/stable/tutorials/shaders/shader_reference/shading_language.html) | GPU shader language (Godot) | Desktop, Web | Godot's [Compatibility renderer](https://docs.godotengine.org/en/stable/tutorials/rendering/renderers.html) supports WebGL 2 for web exports. [MIT engine](https://github.com/godotengine/godot/blob/master/LICENSE.txt). |  |
| [Firebird (SQL with UDR engine)](https://www.firebirdsql.org/file/documentation/pdf/en/firebirddocs/udr-pascal/udr-pascal.pdf) | Compiled queries (Firebird server) | Server | The UDR engine exposes external functions from modules compiled in C, C++, or Pascal. [Firebird source and license](https://github.com/FirebirdSQL/firebird). |  |
| [Scilab](https://help.scilab.org/docs/2025.1.0/en_US/link.html) | Interpreted | Desktop | `link` loads compiled C or Fortran routines for use from numerical scripts. [Source and license](https://gitlab.com/scilab/scilab). |  |
| [SuperCollider](https://github.com/supercollider/supercollider/wiki/Plugins-development) | Managed VM with native audio server | Desktop | Synth definitions can use audio processing units implemented by compiled C++ server plugins. [Source and license](https://github.com/supercollider/supercollider). |  |
| [KornShell (ksh93u+m)](https://man.omnios.org/man1/ksh93) | Interpreted shell | Desktop (CLI) | On systems with dynamic loading, `builtin -f` loads a native library that provides callable shell builtins. [EPL-2.0 implementation](https://github.com/ksh93/ksh). |  |
| [GP (PARI/GP)](https://pari.math.u-bordeaux.fr/dochtml/ref-stable/Programming_in_GP__other_specific_functions.html) | Interpreted or compiled through GP2C | Desktop | `install` makes native PARI functions or compatible functions from other C shared libraries callable from GP. [Project licensing FAQ](https://pari.math.u-bordeaux.fr/faq.html). |  |
| [Modelica](https://specification.modelica.org/maint/3.6/functions.html) | Systems modeling and simulation | Desktop (OpenModelica) | External function declarations can call C functions from models. [OpenModelica](https://github.com/OpenModelica/OpenModelica). |  |
| [Csound](https://csound.com/csound-plugins.html) | Audio synthesis language (Csound runtime) | Desktop | Programs can use native plugins that add opcodes. A game or app can embed Csound through its [C API](https://csound.com/docs/api/). [Source and license](https://github.com/csound/csound). |  |
| [Maxima (SBCL backend)](https://maxima.sourceforge.io/docs/manual/Program-Flow.html) | Computer algebra language (Common Lisp host) | Desktop | Maxima can call Lisp functions. On the SBCL backend, those functions can wrap native C calls through [SBCL's foreign function interface](https://www.sbcl.org/manual/#Foreign-Function-Interface). [Supported Lisp implementations](https://maxima.sourceforge.io/lisp.html). |  |
| [Dafny (Go backend)](https://dafny.org/v4.6.0/Compilation/Go) | Native (via Go) | Desktop, Server | `{:extern}` declarations can call existing Go functions compiled into the generated program. [MIT compiler](https://github.com/dafny-lang/dafny). |  |
| [TVM TensorIR](https://tvm.apache.org/tirx/reference/python/generated/tvm_tirx.tirx.call_extern.html) | Compiled tensor DSL (Apache TVM) | CPU, GPU | `call_extern` emits calls to external functions on supported targets. [Apache 2.0](https://github.com/apache/tvm/blob/main/LICENSE). |  |
| [Ink (C# runtime)](https://github.com/inkle/ink/blob/master/Documentation/RunningYourInk.md) | Interpreted (embedded runtime) | Desktop (inside a host) | Interactive narrative scripts can call functions registered by the host through `BindExternalFunction`. [MIT](https://github.com/inkle/ink). |  |
| [Halide](https://halide-lang.org/docs/api/generated/Pipeline.html) | Compiled image-processing DSL | Desktop | `define_extern` lets compiled pipelines invoke external C functions. [MIT](https://halide-lang.org/). |  |
| [CIL assembly (.NET ILAsm)](https://ecma-international.org/publications-and-standards/standards/ecma-335/) | Managed intermediate language (.NET) | Desktop, Server | `pinvokeimpl` declares methods implemented in a native library. [Open-source IL assembler and runtime](https://github.com/dotnet/runtime). |  |
| [Hack (HHVM)](https://github.com/facebook/hhvm/wiki/Extension-API) | JIT (HHVM) | Server | The HHVM Native Interface exposes C++ functions to code running on HHVM. [Open-source runtime](https://github.com/facebook/hhvm). |  |
| [GAP](https://docs.gap-system.org/doc/ref/chap76.html) | Interpreted | Desktop | C kernel modules can be dynamically loaded and expose compiled functions to GAP programs. [Project source and license](https://github.com/gap-system/gap). |  |
| [Chapel](https://chapel-lang.org/docs/technotes/extern.html) | Native | Desktop | C interoperability through `extern proc`, C headers, and inline C blocks. |  |
| [Faust](https://faustdoc.grame.fr/manual/syntax/) | Compiled DSP language | Desktop, Web/WASM | The `ffunction` primitive declares foreign functions on compatible compiler backends. Faust can be used for audio effects and synthesis. [Compiler license](https://github.com/grame-cncm/faust/blob/master-dev/COPYING.txt). |  |
| [ChucK](https://chuck.stanford.edu/extend/) | Managed VM | Desktop | Scripts can use compiled C++ extensions called chugins for audio processing and other native functionality. [Source and license](https://github.com/ccrma/chuck). |  |
| [Singular](https://www.singular.uni-kl.de/Manual/modules.pdf) | Interpreted | Desktop | Supports static and dynamic C/C++ modules that register callable procedures. [Source and license](https://github.com/Singular/Singular). |  |
| [ISPC](https://ispc.github.io/ispc.html) | Native (SIMD compiler) | Desktop | SIMD kernels can call native host functions declared with `extern "C"`. [Compiler and license](https://github.com/ispc/ispc). |  |
| [Pony](https://tutorial.ponylang.io/c-ffi/calling-c.html) | Native | Desktop | Native C FFI, with external function declarations and calls. |  |
| [Idris 2 (Chez Scheme backend)](https://idris2.readthedocs.io/en/latest/ffi/ffi.html) | Backend-dependent | Desktop | `%foreign` declarations, including C FFI on supporting backends. |  |
| [Agda (JavaScript backend)](https://agda.readthedocs.io/en/latest/language/foreign-function-interface.html) | JS engine (JavaScript backend) | Web | `COMPILE JS` pragmas can wrap browser WebGL calls. [MIT compiler](https://github.com/agda/agda/blob/master/Agda.cabal). |  |
| [Ltac2 (Rocq)](https://rocq-prover.org/doc/v9.2/corelib/Ltac2.Std.html) | Proof tactic language | Desktop (inside Rocq) | `Ltac2 @external` binds tactics and other functions provided by compiled OCaml plugins. [Rocq source and license](https://github.com/rocq-prover/rocq). |  |
| [Cranelift IR (CLIF)](https://github.com/bytecodealliance/wasmtime/blob/main/cranelift/docs/ir.md) | Compiler intermediate language | Desktop, Server | Externally declared functions can be called using signatures with a native platform calling convention. [Cranelift source and license](https://github.com/bytecodealliance/wasmtime). |  |
| [QBE IL](https://c9x.me/compile/doc/il-v1.2.html) | Compiler intermediate language | Desktop, Server | The `call` instruction can invoke native symbols using the platform's C ABI. [QBE source](https://c9x.me/compile/code.html). |  |
| [KCL](https://www.kcl-lang.io/docs/0.12/reference/plugin/overview) | Compiled configuration language | Desktop, Server | KCL programs can import plugins that register compiled Go or Rust functions. [Apache 2.0](https://github.com/kcl-lang/kcl). |  |
| [Lox (clox)](https://craftinginterpreters.com/calls-and-functions.html#native-functions) | Managed VM | Desktop | The C implementation registers native functions that Lox code can call. [Source and license](https://github.com/munificent/craftinginterpreters). |  |
| [GNU Prolog](https://www.gprolog.org/manual/gprolog.html) | Native (GNU Prolog compiler) | Desktop | The `foreign` directive binds predicates to C functions linked into the program. GPL or LGPL. |  |
| [XSB Prolog](https://xsb.sourceforge.net/shadow_site/manual2/node31.html) | Managed VM | Desktop | Its foreign module interface makes compiled C functions callable from Prolog. [Project manual and license information](https://xsb.sourceforge.net/manual1/manual1.pdf). |  |
| [ECLiPSe CLP](https://eclipseclp.org/doc/embedding.pdf) | Managed VM | Desktop | Its external predicate interface exposes routines implemented in C. [MPL-derived license](https://eclipseclp.org/licence.html). |  |
| [J](https://www.jsoftware.com/docs/help802/dictionary/dx015.htm) | Interpreted | Desktop | `15!:0`, commonly named `cd`, calls native library functions. [GPLv3 option](https://github.com/jsoftware/jsource). |  |
| [GNU APL](https://www.gnu.org/software/apl/) | Interpreted | Desktop | GNU APL can bind native extension libraries as callable functions using `⎕FX`. Primarily used for array programming. [Native function example](https://lists.gnu.org/archive/html/bug-apl/2014-05/msg00074.html). |  |
| [Poly/ML (Standard ML)](https://github.com/polyml/polyml) | Native | Desktop | The `Foreign` library exposes functions from native libraries as ML functions. |  |
| [Boo](https://github.com/boo-lang/boo/wiki/Invoke-Native-Methods-with-DllImport) | Managed VM (.NET) | Desktop | Uses .NET P/Invoke through `DllImport` to call native libraries. [BSD-licensed compiler](https://github.com/boo-lang/boo). |  |
| [Open Object Rexx (ooRexx)](https://www.oorexx.org/docs/pdf/rexxapi.pdf) | Interpreted | Desktop | The C/C++ extension API lets compiled packages register native routines and methods that Rexx code can call. [Source repository](https://github.com/ooRexx/ooRexx). |  |
| [Regina Rexx](https://sourceforge.net/projects/regina-rexx/) | Interpreted | Desktop, Server | `RxFuncAdd` loads and registers compiled routines from external function packages. [Example from the interpreter maintainer](https://rexxsql.sourceforge.net/doc/rexxsql.pdf). |  |
| [Elvish](https://pkg.go.dev/src.elv.sh/pkg/eval#NsBuilder.AddGoFn) | Interpreted shell | Desktop (CLI or embedding host) | The Go evaluator's `AddGoFn` API exposes compiled Go functions to Elvish code. [Source and license](https://github.com/elves/elvish). |  |
| [Cmajor](https://cmajor.dev/docs/LanguageReference) | Compiled DSP language | Desktop (inside a host) | A native host can supply external functions as C function pointers. [GPLv3 or later option](https://cmajor.dev/docs/Licence). |  |
| [nesC](https://github.com/tinyos/nesc) | Native (via C) | Embedded (TinyOS) | This C dialect can use native C functions alongside TinyOS components. GPLv2 compiler. |  |
| [Limbo (Inferno)](https://inferno-os.org/inferno/limbo.html) | Managed VM (Dis) | Desktop, Embedded (Inferno) | Limbo code calls native C functions through built-in modules such as `Sys`. [Inferno source license notice](https://github.com/inferno-os/inferno-os/blob/master/NOTICE). |  |
| [YAP Prolog](https://github.com/vscosta/yap-6.3) | Managed VM | Desktop | Its C interface supports foreign predicates implemented in compiled C. The project documents Artistic licensing and separately licensed components. |  |
| [S-Lang](https://www.jedsoft.org/slang/doc/html/cslang-5.html) | Interpreted (embedded) | Desktop | C functions exposed as interpreter intrinsics and dynamically loaded modules. [Open-source license terms](https://www.jedsoft.org/slang/license.html). |  |
| [GNU Data Language (GDL)](https://github.com/gnudatalanguage/gdl) | Interpreted | Desktop | This open-source implementation of IDL supports `CALL_EXTERNAL` for functions in native shared libraries. Primarily used for scientific analysis. [Project change log](https://github.com/gnudatalanguage/gdl/blob/master/NEWS). |  |
| [Scryer Prolog](https://github.com/mthom/scryer-prolog) | Managed VM | Desktop | `use_foreign_module` loads native libraries on FFI-enabled builds. [Binding example](https://github.com/jjtolton/scrypy). |  |
| [Logtalk (SWI-Prolog backend)](https://logtalk.org/handbook/userman/predicates.html) | Logic programming (compiled to Prolog) | Desktop | Logtalk can call C predicates already loaded through the Prolog backend's FFI. [Apache 2.0 toolchain](https://logtalk.org/download.html). |  |
| [Bluespec SystemVerilog](https://bluespec.dev/user-guide/designing-with-bluespec-link.html) | Hardware description and synthesis | Simulation (BSC) | `import "BDPI"` connects Bluespec code to C functions in simulation. [Open-source compiler](https://github.com/B-Lang-org/bsc). |  |
| [Curry (KiCS2)](https://www.curry-lang.org/kics2/download/kics2-3.4.0-manual.pdf) | Native (via KiCS2 and GHC) | Desktop | KiCS2 implements Curry `external` operations with Haskell wrappers, which can use GHC's C FFI. [Compiler license](https://github.com/curry-language/kics2/blob/master/LICENSE). |  |
| [Mercury](https://www.mercurylang.org/information/doc-latest/mercury_user_guide/Foreign-language-interface.html) | Native (C backend) | Desktop | C native integration through `pragma foreign_proc` on C-targeting backends. |  |
| [SML#](https://smlsharp.github.io/) | Native | Desktop | This Standard ML implementation and extension uses `_import` to bind C functions. MIT. |  |
| [P (C# backend)](https://p-org.github.io/P/manual/foreigntypesfunctions/) | State-machine language (.NET) | Desktop, Server | P foreign functions can be implemented by C# wrappers that invoke native libraries through .NET P/Invoke. [MIT compiler](https://github.com/p-org/P). |  |
| [X10](https://x10-lang.org/documentation/practical-x10-programming/native-code-integration.html) | Native (C++ backend) or JVM | Desktop | Supports native code annotations, including C++ code on the native backend. Graphics integration would need bindings. [License](https://github.com/x10-lang/x10/blob/master/LICENSE.html). |  |
| [Picat](https://picat-lang.org/download/picat_guide_html/picat_guide.html) | Managed VM | Desktop | Programs can call predicates written in C and compiled into the runtime. Primarily used for logic and constraint programming. MPL-licensed C sources. |  |
| [CakeML](https://github.com/CakeML/cakeml/blob/master/how-to.md) | Native | Desktop | Compiled programs use linked C implementations for foreign functions. [BSD-3-Clause compiler](https://github.com/CakeML/cakeml). |  |
| [Calc (lcn2/calc)](https://github.com/lcn2/calc/blob/master/calc.man) | Interpreted calculator language | Desktop (CLI) | The `custom()` builtin can call custom C routines compiled into the interpreter. [Source and license](https://github.com/lcn2/calc). |  |
| [Céu](https://ceu-lang.github.io/ceu/out/manual/v0.30/) | Native (via C) | Desktop, Embedded | Reactive programs can call C functions and include native C blocks. [Open-source compiler](https://github.com/ceu-lang/ceu). |  |
| [Gluon](https://github.com/gluon-lang/gluon) | Managed VM (embedded) | Desktop (inside a Rust host) | Rust hosts can pass compiled functions directly to scripts. |  |
| [Heptagon/BZR (C backend)](https://opam.ocaml.org/packages/heptagon/) | Synchronous dataflow (compiled to C) | Desktop, Embedded | Interface declarations import external functions for the C backend. [Compiler manual](https://gitea.auro.re/jeltz/heptagon/raw/commit/9687050f2542f794dac72c99a9c31f647afbc1c0/manual/heptagon-manual.pdf). GPLv3-or-later compiler. |  |
| [Lisp Flavoured Erlang (LFE)](https://github.com/lfe/lfe) | Managed VM (BEAM) | Desktop, Server | LFE can call Erlang modules backed by native functions loaded through [Erlang NIFs](https://github.com/erlang/otp/blob/master/erts/doc/references/erl_nif.md). Apache 2.0 compiler. |  |
| [MLKit (Standard ML)](https://www.mlton.org/guide/20210117/MLKit) | Native (MLKit with Regions) | Desktop | The native MLKit backend supports foreign calls to C. [GPLv2 compiler and MIT runtime](https://github.com/melsman/mlkit). |  |
| [Moscow ML (Standard ML)](https://mosml.org/mosmllib/) | Managed VM | Desktop | The `Dynlib` module dynamically links C libraries and exposes callable symbols. [Compiler license](https://mosml.org/manual.pdf). |  |
| [occam-pi (KRoC)](https://www.cs.kent.ac.uk/projects/ofa/kroc/cif.html) | Concurrent systems language | Desktop (KRoC) | KRoC provides a C interface for calling native code from processes. [Open-source compiler](https://github.com/concurrency/kroc). |  |
| [Pure](https://agraef.github.io/pure-docs/pure.html) | JIT (LLVM) | Desktop | `extern` declarations call C functions; supports native-library integration. GPLv3+ interpreter; LGPLv3+ runtime/libraries. |  |
| [Qore](https://www.qoretechnologies.com/manual/qorus/latest/qore/lang/html/qore_modules.html) | Interpreted | Desktop, Server | Loads native C++ modules. Primarily used for application and service integration. [Open-source license options](https://qore.org/). |  |
| [Risor](https://risor.io/docs/go-embedding) | Managed VM (embedded) | Desktop (inside a Go host) | Automation and embedding language. A Go host can expose functions to scripts. [Apache 2.0](https://github.com/risor-io/risor). |  |
| [Rye](https://github.com/refaktor/rye) | Interpreted | Desktop (inside a Go host) | Go code can expose project-specific functions as [Rye built-ins](https://pkg.go.dev/github.com/refaktor/rye/projectbuiltins). |  |
| [ScriptBasic](https://scriptbasic.org/docs/ug/ug_24.html) | Interpreted | Desktop | Loads compiled C extensions as dynamic libraries. [LGPL interpreter](https://scriptbasic.org/docs/ug/ug_1.html). |  |
| [Yorick](https://yorick.sourceforge.net/manual/yorick_96.php.html) | Interpreted | Desktop | A numerical language whose `extern` interface makes compiled routines callable from scripts. [BSD-licensed implementation](https://github.com/llnl/yorick). |  |
| [Asp](https://github.com/asplang/asp) | Managed VM (embedded) | Embedded (inside a C host) | Scripts call functions exposed by native application libraries. [Example C libraries](https://github.com/asplang/asp-lib). MIT. |  |
| [Blech](https://www.blech-lang.org/docs/user-manual/declarations/) | Reactive embedded systems language | Embedded, Desktop | External declarations can bind C functions and variables. [Apache 2.0 compiler](https://github.com/boschresearch/blech). |  |
| [Cieto](https://github.com/the0cp/cieto) | Managed VM (embedded) | Desktop (inside a C host) | An embedding application can register native C functions that Cieto scripts call. GPLv3. |  |
| [Cogent](https://github.com/au-ts/cogent/blob/master/docs/quickstart.rst) | Native (via C) | Desktop | The compiler integrates hand-written C through its foreign interface. Designed for verified systems code. [Source and licenses](https://github.com/au-ts/cogent). |  |
| [FatScript (fry native builds)](https://www.fatscript.org/pdf/fatscript_v3_en.pdf) | Interpreted (fry) | Desktop (CLI) | The `fat.bridge` library dynamically loads and calls C functions on native builds. [GPLv3 interpreter](https://www.fatscript.org/en/). |  |
| [Gentee](https://github.com/gentee/gentee/) | Managed VM (embedded) | Desktop (inside a Go host) | Automation language with a Go embedding interface for custom functions. |  |
| [Gwion](https://gwion.github.io/Gwion/Contributing/Plugins/introduction.html) | Audio scripting runtime | Desktop | Compiled C plugins register functions and types callable from audio scripts. [GPLv3](https://github.com/Gwion/Gwion). |  |
| [Mog](https://github.com/voltropy/mog) | Native (embedded JIT) | Desktop (inside a host) | The runtime lets embedding applications register callable host functions. MIT. |  |
| [Rex](https://github.com/peterkelly/rex) | Interpreted (embedded) | Desktop (inside a Rust host) | A Rust host can register synchronous or asynchronous native functions for workflow scripts. MIT. |  |
| [Wybe](https://github.com/pschachte/wybe/blob/master/WYBE.md) | Native (LLVM) | Desktop | Foreign calls can specify C as their target language. [MIT compiler](https://github.com/pschachte/wybe). |  |
| [Zygo (zygomys)](https://github.com/glycerine/zygomys) | Interpreted (embedded) | Desktop (inside a Go host) | Calls compiled Go methods on host-provided Go values. BSD-2-Clause. |  |
