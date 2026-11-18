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
running the generator themselves. Each language name in the tables below links to its
repo when one exists.

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

### Tier 0 – Games (https://allbinary.itch.io), thick clients, and services run

- Windows
- Linux - thick clients and services

### Tier 1 – Games (https://allbinary.itch.io) run

- Android
- Web via HTML5/JavaScript from GWT and 99% raw javascript

### Tier 2 – Games run but not currently deployed

- J2ME/MIDP (currently limited to 2d legacy AllBinary games)
- Linux - games
- HarmonyOS 5+ via ArkTS
- macOS
- iOS

### Tier 3 – Planned

- Web via WebAssembly (WASM)
- Samsung Tizen
- LG webOS
- Amazon Fire TV — Fire OS
- Amazon Fire TV — Vega OS
- KaiOS
- Nintendo Switch
- Nintendo Switch 2
- PlayStation
- Xbox - DirectX 12 and Microsoft GDK (I have a unreleased game that uses DirectX already)
- Steam Deck / SteamOS - builds on existing Linux support; Steam-specific features could be added
- Android TV / Google TV - builds on existing Android support
- Apple TV / tvOS

## Current build state

### Tier 0 – Games (https://allbinary.itch.io), thick clients, and services run

The public repo, private repo, and GDevelop poms for server, thick client, and J2ME client code all build and deploy.

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| [Java](https://github.com/AllBinary/AllBinary-Platform) | JVM (Android: ART/DEX), JVM embedded in a native executable (Avian VM), JS engine (via GWT) | Desktop (J2SE), Server, J2ME (legacy games), Mobile (Android), Web (PlayN 1 and 2, all compiled by GWT) | For Web, GWT compiles the whole Java app to JavaScript. GraalVM native works as of 2024, but is not deployed. |

### Tier 1 – Games (https://allbinary.itch.io) run

The public repo, private repo, and GDevelop JSON conversion all build. J2ME-style client code builds, and AllBinary legacy and supported GDevelop JSON games run.

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| [JavaScript](https://github.com/AllBinary/AllBinary-Platform-Javascript) | JS engine | Web | Produced from the TypeScript output by `tsc`; only about 1% of the code still comes from GWT. Allows dynamic preview of games. |
| [Kotlin](https://github.com/AllBinary/AllBinary-Platform-KMP) | JVM (Android: ART/DEX; KMP: Native, JS engine via Kotlin/JS and Kotlin/Wasm) | Desktop, Mobile (Android; iOS via KMP), Web/WASM (via KMP) | |

### Tier 2 – AllBinary legacy game(s) (https://allbinary.itch.io) run

The public and private repo root poms build. J2ME-style client code builds, and at least one legacy game runs.

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| [TypeScript](https://github.com/AllBinary/AllBinary-Platform-TypeScript) | JS engine (ArkTS: Managed VM) | Web, Mobile (HarmonyOS via ArkTS) | Also the source for the JavaScript output (via `tsc`). ArkTS compiles to Ark bytecode, which runs on the Ark runtime. |

### Tier 3 – Public root pom build (with 5 private libraries)

The public repo root pom builds, together with the 5 private libraries it needs.

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| [Ada](https://github.com/AllBinary/AllBinary-Platform-Ada) | Native | Desktop | GNAT |
| [C++](https://github.com/AllBinary/AllBinary-Platform-C-CPP) | Native | Desktop | |
| [C#](https://github.com/AllBinary/AllBinary-Platform-CSharp) | Managed VM | Desktop | .NET CLR. NativeAOT could make a true native build later. |
| [Elixir](https://github.com/AllBinary/AllBinary-Platform-Elixir) | Managed VM | Desktop | BEAM. Shares a runtime with Erlang. |
| [Erlang](https://github.com/AllBinary/AllBinary-Platform-Erlang) | Managed VM | Desktop | BEAM VM, built through `escript`. |
| [Go](https://github.com/AllBinary/AllBinary-Platform-Go) | Native | Desktop | |
| [Groovy](https://github.com/AllBinary/AllBinary-Platform-Groovy) | JVM | Desktop (J2SE) | |
| [Haskell](https://github.com/AllBinary/AllBinary-Platform-Haskell) | Native | Desktop | GHC |
| [Haxe](https://github.com/AllBinary/AllBinary-Platform-Haxe) | Native (via the hxcpp C++ target) | Desktop | Haxe can also target JS, JVM, and HashLink. |
| [Julia](https://github.com/AllBinary/AllBinary-Platform-Julia) | JIT (LLVM) | Desktop | Designed for numerical computing. Code compiles on first call, so startup is slower; standalone executables via PackageCompiler or `juliac`. |
| [Lua](https://github.com/AllBinary/AllBinary-Platform-Lua) | Interpreted | Desktop | |
| [OCaml](https://github.com/AllBinary/AllBinary-Platform-Ocaml) | Native | Desktop | ocamlopt (bytecode is also possible) |
| [Object Pascal](https://github.com/AllBinary/AllBinary-Platform-ObjectPascal) | Native | Desktop | Free Pascal |
| [Perl](https://github.com/AllBinary/AllBinary-Platform-Perl) | Interpreted | Desktop | |
| [Python](https://github.com/AllBinary/AllBinary-Platform-Python) | Interpreted | Desktop | |
| [Ruby](https://github.com/AllBinary/AllBinary-Platform-Ruby) | Interpreted | Desktop | |
| [Rust](https://github.com/AllBinary/AllBinary-Platform-Rust) | Native | Desktop | |
| [Scala](https://github.com/AllBinary/AllBinary-Platform-Scala) | JVM | Desktop (J2SE) | Scala Native is a possible later step. |
| [Tcl](https://github.com/AllBinary/AllBinary-Platform-Tcl) | Interpreted | Desktop | |
| [Zig](https://github.com/AllBinary/AllBinary-Platform-Zig) | Native | Desktop | |

### Tier 4 – AllBinaryBase builds (with 5 private libraries)

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| [AngelScript](https://github.com/AllBinary/AllBinary-Platform-AngelScript) | Managed VM (embedded bytecode) | Desktop (inside a host engine) | Converter, runtime, and poms in progress; checked with the ascheck host (AngelScript 2.39.0). Statically typed scripting with C++-like syntax, classes, and interfaces; embedded through a C++ host. |
| [C23](https://github.com/AllBinary/AllBinary-Platform-C-CPP) | Native | Desktop | |
| [ActionScript](https://github.com/AllBinary/AllBinary-Platform-ActionScript) | Managed VM (AIR) | Desktop and Mobile (AIR), Web (Ruffle) | Flash Player reached end of life in 2020; AIR is now maintained by Harman. Matches the Flash/ActionScript target in the diagram. |
| [COBOL](https://github.com/AllBinary/AllBinary-Platform-COBOL) | Native | Desktop | GnuCOBOL compiles through C. |
| [Dart](https://github.com/AllBinary/AllBinary-Platform-Dart) | Native, JS engine (dart2js / WASM) | Desktop, Mobile (via Flutter), Web (JS/WASM) | `dart compile exe` for Desktop; `dart compile js` or `wasm` for Web. |
| [Elm](https://github.com/AllBinary/AllBinary-Platform-Elm) | JS engine | Web | Pure functional; game state is updated through messages (The Elm Architecture). |
| [FORTRAN](https://github.com/AllBinary/AllBinary-Platform-FORTRAN) | Native | Desktop | |
| [Goose](https://github.com/AllBinary/AllBinary-Platform-Goose) | Native (compiles through C) | Desktop | C output builds with MSVC, GCC, or Clang; bundled TinyCC backend for in-process execution. |
| [Grain](https://github.com/AllBinary/AllBinary-Platform-Grain) | JS engine (WASM) | Web | Compiles to WebAssembly; runs in the browser's WASM engine, not as JavaScript. Young language and ecosystem. |
| [Janet](https://github.com/AllBinary/AllBinary-Platform-Janet) | Interpreted (embedded) | Desktop | Embeddable, with a simple C FFI. |
| [Lisp](https://github.com/AllBinary/AllBinary-Platform-Lisp-with-native-call-support) | Native (e.g. SBCL) | Desktop | Code can be redefined while the program runs (REPL). Garbage collected; smaller library ecosystem. |
| [Nim](https://github.com/AllBinary/AllBinary-Platform-Nim) | Native (compiles through C) | Desktop | Raylib/SDL2 bindings. Deterministic memory management (ARC/ORC). Smaller community than C++/C#. |
| [Objective-C](https://github.com/AllBinary/AllBinary-Platform-Objective-C) | Native | Desktop (macOS first), Mobile (iOS) | |
| [Odin](https://github.com/AllBinary/AllBinary-Platform-Odin) | Native | Desktop | Converter, runtime, and poms in progress; checked with `odin check` (dev-2026-11). Data-oriented programming; maintained OpenGL, SDL2, and raylib bindings in its vendor library. |
| [Racket](https://github.com/AllBinary/AllBinary-Platform-Racket) | Native (Chez Scheme backend) | Desktop | Includes 2D graphics libraries. Standalone apps via `raco exe` and `raco distribute`. |
| [Swift](https://github.com/AllBinary/AllBinary-Platform-Swift) | Native | Mobile (iOS, Android), Desktop | Compiles natively on macOS, Linux, and Windows. Android is supported through the official Swift SDK for Android. |
| [Vala](https://github.com/AllBinary/AllBinary-Platform-Vala) | Native (compiles through C) | Desktop (Linux first) | Built on GObject; mostly used with GNOME. |
| [Wren](https://github.com/AllBinary/AllBinary-Platform-Wren) | Interpreted (embedded) | Desktop (inside a host engine) | Designed for embedding in game engines. Requires a host C/C++ engine. |

### Planned – Initial commit not yet added or in progress

Grouped by language type. For planned languages

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| [Squirrel](https://github.com/AllBinary/AllBinary-Platform-Squirrel) | Managed VM (embedded bytecode) | Desktop (inside a host engine) | Converter, runtime, and poms in progress; compiled and loaded with the sqcheck host (Squirrel 3.2, 64 bit integers and double floats). Lightweight object-oriented scripting with a C API for embedding in games. |
| [AssemblyScript](https://github.com/AllBinary/AllBinary-Platform-AssemblyScript) | JS engine (WASM) | Web | Converter, runtime, and poms in progress; checked with asc 0.28 plus a transform that compiles every function (asc only compiles what the entry reaches). TypeScript-like language compiling to WebAssembly; browser integration requires host bindings. |
| [Clojure](https://github.com/AllBinary/AllBinary-Platform-Clojure) | JVM | Desktop (JVM) | Converter, runtime, and poms in progress; compiled with clojure.main (Clojure 1.12.6) and jcheck, which declares every namespace first so classes can refer to each other in cycles. Lisp on the JVM: a converted class is a namespace with run time method dispatch, and java.* classes are the JVM classes through interop. ClojureScript (a separate compiler targeting JavaScript) is not used. |
| [Crystal](https://github.com/AllBinary/AllBinary-Platform-Crystal) | Native (LLVM) | Desktop | Converter, runtime, and poms in progress; checked with `crystal build --no-codegen` (Crystal 1.22.3) and an entry that calls every method (Crystal only type checks the code a program reaches). Ruby-inspired syntax with static typing and type inference: a Java class is a Crystal class, an interface a module, and references are nilable. |
| [D](https://github.com/AllBinary/AllBinary-Platform-D) | Native (LLVM via LDC) | Desktop | Converter, runtime, and poms in progress; checked with `ldc2 -o-` (LDC 1.43, D 2.113) over the runtime, the dependencies, and the project in one run. Object-oriented systems language with garbage collection and C interoperability: a Java class is a D class, an interface a D interface, and every file is a module that imports a generated module of all of them. |
| [Lean 4](https://lean-lang.org/) | Native (compiles through C) | Desktop | Functional programming language and proof assistant with dependent types, reference counting, and C interoperability. |
| [ASM](https://github.com/AllBinary/AllBinary-Platform-ASM) | Native | Desktop | Platform/ISA specific |
| [F#](https://learn.microsoft.com/en-us/dotnet/fsharp/what-is-fsharp) | Managed VM (.NET CLR) | Desktop | ML-family functional language with access to .NET libraries; candidate for MonoGame/FNA integration. |
| [Mojo](https://mojolang.org/docs/manual/get-started/) | Native (MLIR/LLVM) | Desktop (Linux and macOS; Windows via WSL) | Python-inspired syntax with static typing, ownership, and Python/C interoperability. |
| [Scheme](https://github.com/AllBinary/AllBinary-Platform-Scheme) | Native (via Chicken/Gambit C) | Desktop | Many separate implementations; Chicken and Gambit compile through C. |
| [PHP](https://www.php.net/manual/en/intro-whatis.php) | Interpreted (optional JIT) | Desktop (CLI), Server (planned) | PHP 8+ scripting through the CLI; OPcache provides optional JIT compilation. |
| [Beef](https://www.beeflang.org/docs/language-guide/interop/) | Native | Desktop | Static/dynamic library linking through `extern`, DLL imports, and C linkage. |
| [BlitzMax](https://www.blitzmax.org/docs/en/language/interfacing_with_c/) | Native | Desktop | `Import` and `Extern` integrate C source and functions. |
| [C3](https://c3-lang.org/language-common/cinterop/) | Native | Desktop | C ABI compatibility, `extern` declarations, and static/dynamic linking. |
| [V](https://docs.vlang.io/v-and-c.html) | Native (C backend) | Desktop | Calls C functions through declarations, headers, and linked libraries. |
| [Hare](https://harelang.org/documentation/usage/system-libraries.html) | Native | Desktop | C FFI and mixed Hare/C programs. |
| [Factor](https://docs.factorcode.org/content/article-alien.html) | Native (optimizing compiler) | Desktop | Built-in C library interface, including native callbacks. |
| [Forth](https://www.complang.tuwien.ac.at/forth/gforth/Docs-html/Declaring-C-Functions.html) | Implementation-dependent (Gforth) | Desktop | Implementation-specific support; Gforth provides C function declarations and linking. |
| [Raku](https://docs.raku.org/language/nativecall) | Managed VM (Rakudo/MoarVM) | Desktop | `NativeCall` calls functions in native libraries. |
| [R](https://cloud.r-project.org/doc/manuals/r-release/R-exts.html) | Interpreted | Desktop | `.C`, `.Call`, and related interfaces integrate compiled native code. |
| [FreeBASIC](https://www.freebasic.net/wiki/KeyPgExtern) | Native | Desktop | C library interoperability through external declarations and linkage. |
| [Nelua](https://nelua.io/overview/) | Native (compiles through C) | Desktop | `<cimport>` declarations integrate C functions and headers. [MIT](https://nelua.io/faq/). |
| [ATS](https://ats-lang.sourceforge.net/DOCUMENT/INT2PROGINATS/HTML/c2016.html) | Native (compiles through C) | Desktop | Direct C interoperability, external functions, and embedded C code. [ATS/Postiats: GPLv3](https://ats-lang.sourceforge.net/DOWNLOAD/). |
| [Euphoria](https://openeuphoria.org/docs/perform.html) | Interpreted; Native via C translator | Desktop | Calls dynamically loaded C routines and supports callbacks. [OpenEuphoria: open source](https://github.com/OpenEuphoria/euphoria). |
| [Ring](https://ring-lang.github.io/doc1.26/faq.html) | Interpreted (VM) | Desktop | C/C++ extensions expose native functionality. [MIT](https://github.com/ring-lang/ring). |
| [Modula-2](https://gcc.gnu.org/onlinedocs/gcc-13.2.0/gm2/Interface-to-C.html) | Native (GNU Modula-2) | Desktop | GNU Modula-2 supports C calling conventions through `DEFINITION MODULE FOR "C"`. [GNU compiler: GPL](https://gcc.gnu.org/gccmission.html). |
| [Modula-3](https://www.modula3.com/cm3/doc/help/interfaces.html) | Native (CM3) | Desktop | Supports C interoperability, including interfaces to standard C libraries. [CM3: open source](https://github.com/modula3/cm3). |
| [Harbour](https://github.com/harbour/core) | Managed VM | Desktop | Supports invoking native functions through pointers. [Native call implementation](https://github.com/harbour/core/blob/master/src/rtl/hbdyn.c). [Licensing FAQ](https://harbour.github.io/faq). |
| [Umka](https://github.com/vtereshkov/umka-lang/blob/master/doc/api.md) | Interpreted (embedded VM) | Desktop (inside a host application) | A C host registers functions that Umka scripts can call using `umkaAddFunc()`. [BSD-2-Clause](https://github.com/vtereshkov/umka-lang). |
| [Uiua](https://github.com/uiua-lang/uiua/blob/main/changelog.md) | Interpreted | Desktop | The `&ffi` operation calls native functions and is still evolving. The project publishes [raylib bindings](https://github.com/uiua-lang/rayua). [MIT](https://github.com/uiua-lang/uiua). |
| [BQN](https://mlochbaum.github.io/BQN/doc/ffi.html) | Interpreted (CBQN) | Desktop | CBQN implements `•FFI` for calling functions in native shared libraries. [CBQN licensing](https://github.com/dzaima/CBQN#license). |
| [Fennel](https://github.com/bakpakin/Fennel) | Lua runtime | Desktop | Compiles to Lua and can use [native modules exposed through Lua](https://www.lua.org/manual/5.4/manual.html#pdf-package.loadlib). MIT. |
| [ChaiScript](https://chaiscript.com/examples.html) | Interpreted (embedded) | Desktop (inside a C++ host) | A C++ host can expose functions and types to scripts. [License](https://github.com/ChaiScript/ChaiScript/blob/develop/LICENSE). |
| [Luau](https://luau.org/api/) | Managed VM (embedded) | Desktop (inside a C/C++ host) | A C or C++ host can register native functions that scripts can call. [MIT](https://github.com/luau-lang/luau). |
| [Carp](https://github.com/carp-lang/Carp/blob/master/docs/CInterop.md) | Native (compiles through C) | Desktop | A Lisp designed for real-time applications. It can call C functions and ships [SDL bindings](https://github.com/carp-lang/Carp/blob/master/core/SDL.carp). |
| [Kit](https://github.com/kitlang/kit) | Native (compiles through C) | Desktop | A game-oriented language that imports C headers and uses their functions and types. The [experimental Rust compiler](https://github.com/kitlang-dev/kitc) has a documented [raylib package](https://kit-lang.org/docs/2026.7.12/packages/kit-raylib.html). |
| [MiniScript](https://github.com/JoeStrout/raylib-miniscript) | Interpreted (embedded) | Desktop, Web/WASM | Has native raylib bindings. [MSRLWeb](https://github.com/JoeStrout/MSRLWeb) runs games in the browser via raylib and WebAssembly. MIT. |
| [Jolt](https://github.com/jolt-lang/jolt) | Native (Chez Scheme) | Desktop | A Clojure dialect with C ABI interoperability and [raylib examples](https://github.com/jlt-commons/raylib-jlt). EPL 2.0. |
| [Cyber](https://github.com/fubark/cyber) | Managed VM | Desktop | Embeddable scripting language with [raylib bindings](https://github.com/fubark/ray-cyber). MIT. |
| [CLIPS](https://www.clipsrules.net/documentation/v641/apg641.pdf) | Rule engine | Desktop (inside a host) | C hosts can register callable functions. [CLIPSraylib](https://github.com/mrryanjohnston/CLIPSraylib) exposes graphics and input. |
| [ElfScript](https://github.com/ohmtal/ElfScript) | Interpreted (embedded) | Desktop | Embeddable scripting language with game engine examples. The [raylib bindings index](https://github.com/raysan5/raylib/blob/master/BINDINGS.md) lists a native binding. |
| [Elle](https://github.com/acquitelol/elle) | Native (QBE) | Desktop | Compiles through QBE and has a [raylib module](https://github.com/acquitelol/elle/blob/rewrite/std/raylib.le). |
| [Matte](https://github.com/jcorks/matte) | Interpreted (embedded) | Desktop | The [raylib module](https://github.com/jcorks/raylib-matte) exposes native graphics and input functions. |
| [Oberon-2](https://github.com/RobertFlexx/Rayberon) | Native (via Ofront+) | Desktop | Rayberon binds raylib for native 2D and 3D graphics. |
| [Component Pascal](https://github.com/RobertFlexx/Rayberon) | Native (via Ofront+) | Desktop | Rayberon provides bindings for the Component Pascal dialect through Ofront+. |
| [Scopes](https://github.com/Fundament-Software/scopes) | Native | Desktop | The [raylib wrapper](https://github.com/salotz/raylib-scopes) demonstrates native graphics integration. |
| [SmallBASIC](https://smallbasic.github.io/pages/plugins_raylib.html) | Interpreted | Desktop | A native raylib plugin exposes rendering, input, and audio. [Interpreter](https://github.com/smallbasic/SmallBASIC). |
| [Solod](https://github.com/solod-dev/solod) | Native (via C) | Desktop | Provides [raylib bindings](https://github.com/solod-dev/raylib) for native graphics. |
| [Wave](https://github.com/wavefnd/Wave) | Native | Desktop | Has [native raylib bindings](https://github.com/wavefnd/raylib-wave). |
| [Sunder](https://github.com/ashn-dot-dev/sunder) | Native | Desktop | Systems language with [native raylib bindings](https://github.com/ashn-dot-dev/raylib-sunder). |
| [Mach](https://github.com/briar-systems/mach) | Native | Desktop | Systems language with [raylib bindings](https://github.com/Angluca/mach-raylib). |
| [Jule](https://github.com/julelang/jule) | Native (via C++) | Desktop | Supports C/C++ interoperability and has [community raylib bindings](https://github.com/SabeDoesThings/rayjule). |
| [Never](https://github.com/never-lang/never) | Managed VM (embedded) | Desktop | Statically typed functional language with an [older raylib binding](https://github.com/never-lang/raylib-never). |
| [XD Pascal](https://github.com/vtereshkov/xdpw) | Native | Desktop (Windows) | Embeddable Pascal dialect with an [older raylib binding](https://github.com/vtereshkov/raylib-xdpw). |
| [F*](https://github.com/FStarLang/FStar) | Native via Low* and KaRaMeL | Desktop | [KaRaMeL](https://github.com/FStarLang/karamel) compiles the Low* subset to C. The [raylib index](https://github.com/raysan5/raylib/blob/master/BINDINGS.md) lists F* bindings. |
| [Bend](https://github.com/bendlang/bend) | Native (CPU/GPU backends) | Desktop (Linux and macOS first) | Functional language focused on automatic parallelism; current Bend 2 also supports dependent types and proofs. Game runtime and graphics integration would need evaluation. |
| [Standard ML](https://www.mlton.org/ForeignFunctionInterface) | Native (MLton) | Desktop | Implementation-specific support; MLton supports calling C and exporting functions to C. |

### Possible Future - TBD

Without an AllBinary repo, links point to official language sites or documentation.

| Language | Language type | Targets | Notes |
|----------|---------------|---------|-------|
| [GDScript](https://docs.godotengine.org/en/stable/getting_started/step_by_step/scripting_languages.html) | Interpreted (Godot) | Desktop, Mobile, Web (via Godot exports) | Godot's scripting language with optional static typing; planned Godot integration. |
| [Oberon+](https://github.com/AllBinary/AllBinary-Platform-OberonPlus) | Managed VM (CLI/Mono); Native via generated C | Desktop | The OBX compiler emits .NET CLI bytecode, or C99 source for native builds. Few game libraries. |
| [Eiffel](https://www.eiffel.org/doc/solutions/Interfacing_with_C_and_C%2B%2B) | Native | Desktop | External routines interfacing with C and C++. |
| [Dylan](https://package.opendylan.org/opendylan/library-reference/c-ffi/index.html) | Native (Open Dylan) | Desktop | Open Dylan's `c-ffi` library describes and calls C interfaces. |
| [Red/System](https://static.red-lang.org/red-system-specs.html) | Native | Desktop | `#import` imports functions from native shared libraries. |
| [Red](https://www.red-lang.org/) | Interpreted / Native | Desktop | Native integration through its Red/System layer. |
| [Smalltalk / Pharo](https://books.pharo.org/booklet-uffi/) | Managed VM (Pharo) | Desktop | Unified FFI calls native libraries. [Pharo: MIT](https://pharo-project.org/). |
| [Visual Basic .NET](https://learn.microsoft.com/en-us/dotnet/visual-basic/language-reference/statements/declare-statement) | Managed VM (.NET CLR) | Desktop | `Declare` and P/Invoke call native DLL functions. [Roslyn: MIT](https://github.com/dotnet/roslyn). |
| [Berry](https://github.com/berry-lang/berry_mapping) | Interpreted (embedded VM) | Desktop (inside a host engine) | Embedded scripting with C native functions and callbacks. |
| [Daslang (formerly daScript)](https://daslang.io/doc/reference/embedding/external_modules.html) | Interpreted; Native via C++ AOT | Desktop | C/C++ integration through native bindings and external modules; designed for games. [BSD-3-Clause](https://github.com/GaijinEntertainment/daScript). |
| [Gambas](https://gambaswiki.org/wiki/howto/extern) | Interpreted (VM) | Desktop (Linux first) | `EXTERN` and `LIBRARY` call functions in C shared libraries. [GPLv2 or later](https://gambaswiki.org/wiki/doc/license). |
| [Seed7](https://seed7.net/manual/ffi.htm) | Interpreted; Native via C compiler | Desktop | C interoperability through wrapper functions and its foreign-function interface. [GPL; runtime LGPL](https://seed7.net/faq.htm#Which_license_does_Seed7_use). |
| [Io](https://github.com/IoLanguage/io) | Interpreted | Desktop (native branch); Web/WASM (current branch) | Native branch supports `DynLib` and C extensions; its current WebAssembly path uses host integration instead. [BSD/MIT-style](https://iolanguage.org/docs/Guide/). |
| [ReScript](https://www.rescript-lang.org/docs/manual/external/) | JS engine | Web | JavaScript `external` bindings provide a route to WebGL and JS graphics libraries; bindings would need integration. [LGPL compiler; MIT runtime](https://github.com/rescript-lang/rescript#-license). |
| [PureScript](https://github.com/purescript/documentation/blob/master/guides/FFI.md) | JS engine | Web | JavaScript FFI provides a route to WebGL bindings; bindings would need integration. [Open source](https://github.com/purescript/purescript). |
| [CoffeeScript](https://coffeescript.org/) | JS engine | Web | Compiles to JavaScript and interoperates with JS libraries, allowing WebGL calls through JavaScript APIs. [MIT](https://github.com/jashkenas/coffeescript). |
| [LiveScript](https://livescript.net/) | JS engine | Web | Compiles to JavaScript; browser APIs and JS interop provide a route to WebGL. [MIT](https://github.com/gkz/LiveScript). |
| [Pike](https://pike.lysator.liu.se/docs/man/chapter_30.html) | Interpreted (VM) | Desktop | C extension modules expose native functions and libraries. [GPL/LGPL/MPL options](https://github.com/pikelang/Pike). |
| [ReasonML](https://reasonml.github.io/docs/en/interop) | JS engine (via Melange) | Web | Melange JavaScript bindings can expose browser WebGL methods and graphics libraries such as Three.js. [Reason toolchain license](https://github.com/reasonml/reason). |
| [Koka](https://koka-lang.github.io/koka/doc/book.html) | Native (C backend) | Desktop | The C backend can call C functions through `extern` bindings. [C FFI example](https://github.com/koka-lang/koka/discussions/664). |
| [Unicon](https://unicon.sourceforge.io/up/functions.html#loadfunc) | Interpreted (VM) | Desktop | `loadfunc()` loads a C function from a shared library using the Unicon calling interface. [GPL](https://github.com/uniconproject/unicon). |
| [Roc](https://www.roc-lang.org/docs/main/langref/platforms/) | Native | Desktop | A native platform host implements operations that Roc code calls through the platform API. [Project repository](https://github.com/roc-lang/roc). |
| [Lobster](https://github.com/aardappel/lobster/blob/master/docs/source/implementation.md) | JIT or native via C++ | Desktop, Web/WASM (via Emscripten) | Game-oriented language that exposes C++ functions as built-ins. Uses OpenGL and SDL on desktop. Apache 2.0. |
| [Hot Soup Processor (HSP3)](https://www.onionsoft.net/hsp/v36en/doclib/hspprog.htm) | Interpreted | Desktop (DLL calls on Windows) | Scripts can call Windows DLL functions using `#uselib` and `#func`. [OpenHSP: BSD-3-Clause](https://github.com/onitama/OpenHSP). |
| [Felix](https://github.com/felix-lang/felix) | Native (compiles through C++) | Desktop | Bindings integrate existing C and C++ libraries. |
| [Gravity](https://github.com/marcobambini/gravity) | Interpreted (embedded VM) | Desktop (inside a host application) | Embeddable scripting language with native host integration and an Objective-C bridge. MIT. |
| [Phix](https://phix.x10.mx/docs/html/ccall.htm) | Native | Desktop | Calls C shared libraries and provides [OpenGL bindings](https://www.phix.x10.mx/docs/html/opengl_1.1.htm). [OSL 3.0 compiler and AFL 3.0 for most runtime and library components](https://phix.x10.mx/docs/html/licence.htm). |
| [Terra](https://terralang.org/api.html) | Native (embedded in Lua) | Desktop | `terralib.includec()` imports C declarations for calling native functions. [License](https://github.com/terralang/terra/blob/master/release/share/terra/LICENSE.txt). |
| [Jakt](https://github.com/SerenityOS/jakt#inline-c) | Native (compiles through C++) | Desktop | Inline `cpp` blocks let Jakt code call existing C++ functions. |
| [ooc](https://ooc-lang.github.io/docs/lang/covers/) | Native (compiles through C99) | Desktop | `cover` declarations map C types and can expose external functions. [Compiler repository](https://github.com/ooc-lang/rock). |
| [Mint](https://api.mint-lang.com/guide/reference/javascript-interop/inlining) | JS engine | Web | Inline JavaScript can wrap browser WebGL APIs and JavaScript graphics libraries. [BSD-3-Clause compiler](https://github.com/mint-lang/mint). |
| [YueScript](https://dora-ssr.net/docs/tutorial/language-select/) | Lua runtime | Desktop (via Dora SSR) | Compiles to Lua and can call the native APIs exposed by the Dora SSR game engine. [MIT compiler](https://github.com/IppClub/YueScript). |
| [MoonScript](https://moonscript.org/guides/making-games-with-love.html) | Lua runtime | Desktop (via LÖVE) | Compiles to Lua and can call the graphics and input APIs exposed by the LÖVE game framework. [MIT compiler](https://moonscript.org/). |
| [PocketLang](https://github.com/ThakeeNathees/pocketlang/blob/master/src/include/pocketlang.h) | Interpreted (embedded VM) | Desktop (inside a host application) | A C host can register native functions that scripts can call through the embedding API. [MIT](https://github.com/ThakeeNathees/pocketlang). |
| [Rhai](https://rhai.rs/book/rust/functions.html) | Interpreted (embedded) | Desktop (inside a Rust host) | Scripts can call Rust functions registered by the host. A [Bevy plugin](https://github.com/makspll/bevy_mod_scripting) provides game engine integration. |
| [Rune](https://docs.rune.build/languages/rust/) | Managed VM (embedded) | Desktop (inside a Rust host) | Scripts can call Rust functions exported through native modules. [Source repository](https://github.com/rune-rs/rune). |
| [Jinx](https://github.com/JamesBoer/Jinx) | Managed VM (embedded) | Desktop (inside a C++ host) | Designed for games and real-time applications. Scripts can call native functions registered through the C++ API. |
| [Squint](https://github.com/squint-cljs/squint) | JS engine | Web | This ClojureScript dialect compiles to JavaScript. Its interop can reach browser WebGL or libraries such as Three.js through application bindings. |
| [Mimas](https://github.com/imlazyeye/mimas) | Managed VM (embedded) | Desktop (inside a Rust host) | Statically typed scripting for Rust hosts. The Bevy Breakout example exposes engine components and resources to scripts. MIT or Apache 2.0. |
| [Mun](https://mun-lang.org/blog/2020/05/02/this-month-april/) | Native | Desktop (via the Mun runtime) | `extern` declarations let Mun code call registered host functions. The runtime supports hot reloading. [MIT or Apache 2.0](https://github.com/mun-lang/mun). |
| [jank](https://github.com/jank-lang/jank) | Native (LLVM) | Desktop | A native Clojure dialect with C++ interoperability for calling functions from existing libraries. |
| [Teal](https://github.com/teal-language/tl) | Lua runtime | Desktop (including LÖVE) | A typed Lua dialect that can use native APIs exposed to Lua. [LÖVE game example](https://github.com/teal-language/tl/discussions/637). MIT. |
| [Clue](https://github.com/ClueLang/Clue) | Lua runtime | Desktop | Compiles to Lua and can use [Lua's native module system](https://www.lua.org/manual/5.4/manual.html#pdf-package.loadlib). MIT. |
| [ClojureScript](https://clojurescript.org/) | JS engine | Web | Compiles to JavaScript and can use WebGL through JavaScript interop. [thi.ng/geom WebGL examples](https://github.com/thi-ng/geom#opengl--webgl). |
| [Rebol 3](https://github.com/Oldes/Rebol3) | Interpreted | Desktop | The Apache 2.0 licensed Oldes implementation supports C extensions. Its [FMOD extension](https://github.com/Oldes/R3-extension-FMOD) demonstrates calls to a native audio library. |
| [Icon](https://raptor.cs.arizona.edu/icon/uguide/cfuncs.htm) | Interpreted (Icon VM) | Desktop | The University of Arizona implementation loads C functions from shared libraries on supported systems. Icon is the predecessor of Unicon. [Project and source](https://raptor.cs.arizona.edu/icon/). |
| [Processing (Java mode)](https://www.processing.org/tutorials/p3d/) | JVM | Desktop | The sketch language uses JOGL for native OpenGL rendering through `P2D` and `P3D`. Sketches can also use Java libraries. [Source and licenses](https://github.com/processing/processing4). |
| [Pure Data (Pd)](https://github.com/pure-data/externals-howto) | Visual dataflow (Pd runtime) | Desktop | Patches can use compiled C externals. The [Gem extension](https://github.com/umlaeute/Gem) exposes OpenGL rendering. |
| [Adventure Game Studio Script](https://adventuregamestudio.github.io/ags-manual/EnginePluginRun-timeAPI.html) | Managed VM (AGS engine) | Desktop | Native plugins can register functions that point-and-click adventure scripts call. [Engine source](https://github.com/adventuregamestudio/ags). |
| [JStar](https://github.com/bamless/jstar) | Managed VM (embedded) | Desktop (inside a C host) | Offers a C API for native integration. A host can supply game or application APIs. MIT. |
| [TorqueScript](https://docs.torque3d.org/for-programmers/scripting/torquescript/what-is-torquescript) | Interpreted (game engine) | Desktop (Torque3D) | Can call C++ console functions exposed by the [MIT-licensed engine](https://github.com/TorqueGameEngines/Torque3D). |
| [ZScript](https://github.com/zdoom-docs/stable/blob/master/language.md) | Managed VM (game engine) | Desktop (UZDoom) | Calls native engine functions and works with native engine types. [Open-source implementation](https://github.com/UZDoom/UZDoom). |
| [Dyon](https://github.com/PistonDevelopers/dyon) | Interpreted (embedded) | Desktop (inside a Rust host) | A Rust application can expose functions to Dyon scripts through the embedding API. |
| [Lily](https://lily-lang.org/tooling-bindgen.html) | Interpreted (embedded) | Desktop | Bindgen generates bindings for extending Lily with C functions. [Source](https://github.com/fascinatedbox/lily). |
| [Ketos](https://github.com/murarth/ketos) | Interpreted (embedded) | Desktop (inside a Rust host) | Lisp dialect for Rust programs. Hosts can expose Rust functions as foreign functions. |
| [LIL](https://runtimeterror.com/tech/lil/) | Interpreted (embedded) | Desktop | The Little Interpreted Language supports command callbacks registered by its C or Pascal host. |
| [Cicada](https://cicadascript.sourceforge.io/) | Interpreted (embedded) | Desktop | C-like scripting language that can be linked with C code and call host functions. |
| [Jancy](https://vovkos.github.io/jancy/api/) | JIT (LLVM) | Desktop | Embeddable scripting language with C ABI interoperability and a C API. [Source](https://github.com/vovkos/jancy). |
| [Fantom](https://fantom.org/doc/docLang/JavaScript) | JVM or JS engine | Desktop, Web | Compiles to JavaScript. JavaScript peers can wrap browser APIs such as WebGL. [Native peers](https://www.fantom.org/doc/docLang/Natives). |
| [Ceylon](https://www.saylong.org/documentation/tour/dynamic/) | JVM or JS engine | Desktop, Web | The JavaScript backend can access browser APIs through dynamic interop. [The compiler is archived](https://github.com/eclipse-archived/ceylon). |
| [ArkScript](https://arkscript-lang.dev/docs/tutorials/modules/) | Managed VM (embedded) | Desktop | Native C++ modules expose functions to scripts. [Source and license](https://github.com/ArkScript-lang/Ark). |
| [MY-BASIC](https://github.com/paladin-t/my_basic) | Interpreted (embedded) | Desktop (inside a C host) | The interpreter API lets hosts register native functions that BASIC code can call. |
| [Boron](https://urlan.sourceforge.io/boron/) | Interpreted (embedded) | Desktop (inside a C host) | Rebol-inspired language that can be embedded and extended through its C library. |
| [Pawn](https://www.compuphase.com/pawn/pawnfeatures.htm) | Managed VM (embedded) | Desktop (inside a host) | The AMX API registers native functions supplied by the host. [Implementation](https://github.com/compuphase/pawn). |
| [QuakeC](https://github.com/graphitemaster/gmqcc) | Managed VM (game engine) | Desktop (Quake-compatible engine) | Gameplay code calls native engine built-ins for operations such as spawning entities and playing sounds. [Quake engine source](https://github.com/id-Software/Quake). |
| [Quirrel](https://quirrel.io/doc/embedding/bindings.html) | Managed VM (embedded) | Desktop (inside a C++ host) | Squirrel-derived game scripting language with Sqrat bindings for native C++ functions and types. [Source](https://github.com/GaijinEntertainment/quirrel). |
| [SGScript](https://www.sgscript.org/pages/docpages/sgscript.docs.htm) | Managed VM (embedded) | Desktop | The scripting engine's C API exposes native functions and objects. [Source](https://github.com/archo5/sgscript). |
| [Toy](https://github.com/krgamestudios/Toy) | Interpreted (embedded) | Desktop (inside a C host) | Embedded scripts can call native C bindings. zlib license. |
| [ZetScript](https://zetscript.org/) | Managed VM (embedded) | Desktop (inside a C++ host) | C++ applications can register native functions and types for scripts to use. |
| [Pluto](https://github.com/PlutoLang/Pluto) | Managed VM (Lua family) | Desktop | A Lua superset that can reach native code through Lua-style C modules. |
| [GameMonkey Script](https://github.com/publicrepo/gmscript) | Managed VM (embedded) | Desktop (inside a C++ host) | Game-oriented scripting language with bindings registered by its C++ host. MIT. |
| [Pascal Script](https://www.remobjects.com/ps.aspx) | Managed VM (embedded) | Desktop (inside a Pascal host) | Hosts can register native Delphi functions and classes for Pascal Script code. |
| [fe](https://github.com/rxi/fe) | Interpreted (embedded) | Desktop (inside a C host) | Small Lisp-like language that can call C functions registered by its host. |
| [Anko](https://github.com/mattn/anko/) | Interpreted (embedded) | Desktop (inside a Go host) | An embedded scripting environment can expose compiled Go functions and values to scripts. |
| [Tengo](https://github.com/d5/tengo) | Managed VM (embedded) | Desktop (inside a Go host) | Go hosts can expose callable native functions as Tengo objects. |
| [DWScript](https://compendium.dwscript.net/lang/tooling/js_transpiler) | Interpreted or JS engine | Desktop, Web | Delphi-like scripting language with a JavaScript backend that can wrap browser APIs through JavaScript integrations. [Source](https://bitbucket.org/egrange/dwscript/). |
| [WurstScript](https://wurstlang.org/features/backends.html) | Game scripting (Jass or Lua backend) | Inside Warcraft III | The open-source compiler targets scripting runtimes that call game engine natives. [Compiler source](https://github.com/wurstscript/WurstScript). |
| [Neat](https://neat-lang.github.io/manual.html) | Native | Desktop | Supports `extern(C)` declarations and a `cimport` macro for existing C APIs. [BSD-3-Clause](https://github.com/Neat-Lang/neat). |
| [Vox](https://github.com/MrSmith33/vox/blob/master/internals.md) | Native (AOT or JIT) | Desktop | Uses the C calling convention and can bind host function pointers to external declarations. [Boost Software License](https://github.com/MrSmith33/vox). |
| [C2](https://github.com/c2lang/c2compiler) | Native | Desktop | A C-family language with [bindings for libc and other native libraries](https://github.com/c2lang/c2_libs). Apache 2.0. |
| [EndBASIC](https://jmmv.dev/2021/01/embedding-endbasic.html) | Managed VM (embedded) | Desktop, Web | Rust applications can register commands that BASIC programs call. [Runtime and license](https://github.com/endbasic/endbasic). |
| [Mond](https://github.com/Rohansi/Mond/wiki/Automatic-Binding) | Managed VM (embedded in .NET) | Desktop (inside a .NET host) | The binding API exposes C# functions and classes to scripts. Native APIs can be exposed through the host. [Source](https://github.com/Rohansi/Mond). |
| [ReCT](https://github.com/RedCubeDev-ByteSpace/ReCT) | Managed VM (.NET) | Desktop | The [raylib bindings index](https://github.com/raysan5/raylib/blob/master/BINDINGS.md) lists Relib for raylib 3.5. The binding repository could not be independently verified. GPLv3 compiler. |
| [RapydScript-NG](https://github.com/kovidgoyal/rapydscript-ng) | JS engine | Web | Its Python-like syntax compiles to JavaScript and can call browser WebGL APIs through JavaScript interop. BSD-2-Clause. |
| [ZLang](https://github.com/zlangdevs/zlang) | Native (LLVM) | Desktop | Supports direct C interoperability. The project links raylib bindings and a raylib-based game example. |
| [Zen C](https://github.com/Zuhaitz-dev/zenc-lang) | Native (via C) | Desktop | Compiles to C and interoperates with existing native libraries. The project also documents Objective-C integration. |
| [Fun](https://github.com/omdxp/fun) | Native (via C) | Desktop | Compiles to C and provides signatures for C interoperability. MIT. |
| [JustLang](https://github.com/tbit-bdd/JustLang) | Interpreted (embedded) | Desktop | Loads compiled C plugins that register native functions callable from scripts. MIT. |
| [Amber Smalltalk](https://github.com/amber-smalltalk/amber) | JS engine | Web | Browser-oriented Smalltalk implementation that compiles to JavaScript. Browser WebGL can be reached through JavaScript wrappers. MIT. |
| [MetaScript](https://github.com/metascript/metascript) | JS engine | Web | Compiles to JavaScript. Browser WebGL can be reached through JavaScript calls in the generated program. MIT. |
| [LispyScript](https://github.com/santoshrajan/lispyscript) | JS engine | Web | A Lisp-style language that compiles to JavaScript and can reach browser WebGL through JavaScript calls. |
| [Volt](https://github.com/VoltLang/Amp) | Native (LLVM) | Desktop | Amp provides bindings to C libraries including SDL2, SDL2_image, and SDL2_ttf. [Compiler and license](https://github.com/VoltLang/Volta). |
| [Kairo](https://kairolang.org/docs/language/c-cpp/) | Native | Desktop | Supports C and C++ interoperability. [Apache 2.0 compiler with a runtime library exception](https://github.com/kairolang/kairo). |
| [Neko](https://nekovm.org/doc/ffi/) | Managed VM | Desktop | Neko programs call native primitives exposed through C wrappers. [Source and license](https://github.com/HaxeFoundation/neko). |
| [Imba](https://github.com/imba/imba) | JS engine | Web | MIT-licensed web UI language. Compiles to JavaScript and can call browser WebGL APIs through JavaScript interop. |
| [Civet](https://github.com/DanielXMoore/Civet) | JS engine | Web | An MIT-licensed TypeScript superset that can reach WebGL through browser JavaScript APIs. |
| [Cakelisp](https://github.com/jduhamel/cakelisp) | Native (via C/C++) | Desktop | A game-oriented compiled language with direct access to C and C++ declarations and functions. The project links its upstream source repository. |
| [Clay](https://github.com/jckarter/clay/blob/master/doc/language-reference.md#external-functions) | Native | Desktop | The `external` declaration calls C functions and can export Clay functions with C linkage. [Project and license](https://github.com/jckarter/clay). |
| [Cone](https://cone.jondgoodwin.com/coneref/refinclude.html) | Native (LLVM) | Desktop | An experimental systems language. `extern` declares C-compatible functions. [Compiler and license](https://github.com/jondgoodwin/cone). |
| [Feral](https://github.com/Feral-Lang/Feral) | Managed VM (embedded) | Desktop | An MIT-licensed scripting language that can use native modules. [LibPG](https://github.com/Feral-Lang/LibPG) exposes PostgreSQL's C library. |
| [GOAL (OpenGOAL)](https://opengoal.dev/docs/porting-info/porting_to_x86/) | Native (OpenGOAL compiler) | Desktop | The ISC-licensed implementation compiles GOAL to x86-64 and integrates with a C++ game runtime. [Source and license](https://github.com/open-goal/jak-project). |
| [FuelLang](https://github.com/awemorris/fuel) | Game scripting (Fuel engine) | Desktop (Fuel engine) | The MIT-licensed Fuel engine provides hardware-accelerated rendering and a built-in language for writing games. |
| [GorillaScript](https://github.com/ckknight/gorillascript) | JS engine | Web | Compiles to JavaScript. Browser graphics APIs such as WebGL can be accessed through JavaScript calls. MIT. |
| [Sibilant](https://github.com/jbr/sibilant) | JS engine | Web | A Lisp-style language that compiles to JavaScript and can call WebGL through the JavaScript host. MIT. |
| [Vale](https://vale.dev/guide/externs) | Native | Desktop | `extern` declarations call C functions and exports can be called from C. [Apache 2.0 compiler](https://github.com/ValeLang/Vale/blob/master/LICENSE). |
| [QB64](https://qb64.com/wiki/DECLARE-DYNAMIC-LIBRARY.html) | Native (via C++) | Desktop | Extended QBasic with `DECLARE DYNAMIC LIBRARY` for native shared libraries. [QB64 Phoenix Edition source and licenses](https://github.com/QB64-Phoenix-Edition/QB64pe). |
| [QBJS](https://github.com/boxgaming/qbjs/wiki/QBasic-Language-Support) | JS engine | Web | A QBasic dialect for the browser. Its inline JavaScript blocks can call browser APIs including WebGL. [MIT](https://github.com/boxgaming/qbjs). |
| [HamsterSpeak](https://rpg.hamsterrepublic.com/ohrrpgce/HamsterSpeak_Specification) | Game scripting (OHRRPGCE) | Desktop (OHRRPGCE) | Scripts call built-in commands implemented by the RPG engine. [Engine source and licenses](https://github.com/ohrrpgce/ohrrpgce). |
| [Objeck](https://www.objeck.org/doc_native_interface.html) | Managed VM | Desktop | C++ native modules expose functions to Objeck. The project provides [SDL2 bindings](https://www.objeck.org/api/latest/game.sdl2-surface.html?scrollTo=game.sdl2). [Source and license](https://github.com/objeck/objeck-lang). |
| [Genie](https://docs.vala.dev/genie/introduction/01-compiling.html) | Native (via C) | Desktop | Uses Vala’s compiler and VAPI bindings to call C libraries. The [Genie guide](https://docs.vala.dev/genie/) includes GTK examples. |
| [Citrine](https://citrine-lang.org/faq.ctr) | Interpreted | Desktop | Loads compiled C plugins as shared libraries. Its Media plugin uses SDL for apps and games. [BSD source license](https://citrine-lang.org/credits.ctr). |
| [BBC BASIC](https://www.bbcbasic.co.uk/bbcsdl/manual/bbcsdle.html) | Interpreted (BBCSDL) | Desktop, Mobile, Web/WASM | On native BBCSDL builds, `SYS` calls SDL or functions in external shared libraries. [Zlib-licensed implementation](https://github.com/rtrussell/BBCSDL). |
| [eC](https://github.com/ecere/ecere-sdk) | Native (via C) | Desktop | The Ecere SDK provides GUI and OpenGL integration. The BSD-licensed [eC toolchain](https://github.com/ecere/eC) compiles through C. |
| [BaCon](https://www.basic-converter.org/documentation.html) | Native (via C) | Desktop | This BASIC dialect uses `IMPORT` to call functions from native libraries. Its HUG library supports several GUI toolkits. [MIT](https://chiselapp.com/user/bacon/repository/bacon/home). |
| [X11-Basic](https://x11-basic.sourceforge.net/X11-Basic-manual.pdf) | Interpreted or native (via C) | Desktop | `LINK`, `SYM_ADR`, and `CALL` support calling routines in shared libraries. Includes graphics and sound commands. GPLv2 or later. |
| [XBLite](https://xblite.org/whats-new/) | Native | Desktop (Windows) | `EXTERNAL CFUNCTION` declares native routines. XBLite is a [GPL-licensed XBasic dialect](https://xblite.org/) oriented toward Windows application development. |
| [Suneido](https://suneido.com/downloads/) | Managed VM | Desktop (Windows for DLL calls) | Suneido’s DLL interface calls native libraries. The project supplies database and application UI tools. [MIT-licensed Go implementation](https://github.com/apmckinlay/gsuneido). |
| [JSX (jsx/JSX)](https://github.com/jsx/JSX) | JS engine | Web | A typed language that compiles to JavaScript and can use WebGL through browser bindings. [WebGL example](https://gist.github.com/Santarh/2890489). MIT. |
| [Earl Grey](https://github.com/breuleux/earl-grey) | JS engine | Web | Compiles to JavaScript. Browser WebGL can be reached through JavaScript interop. MIT. |
| [Hike](https://github.com/kanryu/hike-lang) | Native (LLVM), WASM | Desktop, Web/WASM | Supports direct C ABI interoperability on native targets. The compiler is MIT-licensed. |
| [Ingle](https://github.com/ingle-lang/ingle) | Native | Desktop | `extern "c"` calls C functions. The project provides an opt-in immediate-mode graphics module. MIT. |
| [Jux](https://github.com/xdsswar/juxlang) | Native (via Rust) | Desktop | Experimental compiler with C and C++ FFI. Apache 2.0. |
| [Teko](https://github.com/teko-org/teko-lang) | Native (AOT) | Desktop | The self-hosting compiler supports C FFI. Apache 2.0 or MIT. |
| [Nari](https://github.com/nari-lang/nari) | JIT | Desktop | Provides an FFI for calling C functions. GPLv3. |
| [JHYY](https://github.com/JiHuiYiYou/JiHuiYiYou-compiler) | Native (x86-64) | Desktop | Uses the C ABI for foreign calls and generates native Windows and Linux binaries. MIT. |
| [Ur/Web](https://github.com/urweb/urweb) | Native (server), JS engine (client) | Server, Web | Its FFI links server-side C and maps client-side calls to JavaScript wrappers, which can call WebGL. [FFI manual](https://github.com/urweb/urweb/blob/master/doc/manual.tex). |
| [Links](https://github.com/links-lang/links/wiki/JavaScript-FFI) | Interpreted (server), JS engine (client) | Server, Web | The `alien javascript` interface exposes browser-side JavaScript functions, including wrappers around WebGL. [Project source](https://github.com/links-lang/links). |
| [Pyret](https://pyret.org/docs/latest/Internals.html) | JS engine | Desktop (Node.js), Web | JavaScript modules can expose functions to Pyret programs. A browser module can wrap WebGL calls. [Compiler source](https://github.com/brownplt/pyret-lang). |
| [Opa](https://opalang.org/) | JS engine | Server, Web | Compiles client-side code to JavaScript and supports JavaScript libraries for browser integration. [AGPL compiler and MIT library](https://github.com/mlstate/opalang). |
| [Lumen (Lisp)](https://github.com/sctb/lumen) | JS engine or Lua runtime | Web, Desktop | Compiles to JavaScript or Lua. Browser builds can use JavaScript wrappers to call WebGL. BSD-2-Clause. |
| [Magnesium](https://github.com/QNSET-Software-Development/magnesium) | Managed VM (embedded) | Desktop (inside a host) | A host can register native functions or use the runtime’s numeric C function-pointer FFI. MIT. |
| [Objective-J](https://www.cappuccino.dev/learn/objective-j.html) | JS engine | Web | The Cappuccino language interoperates directly with JavaScript, which gives browser code access to WebGL. [LGPL](https://www.cappuccino.dev/learn/). |
| [QML](https://doc.qt.io/qt-6/qtqml-cppintegration-exposecppattributes.html) | Declarative UI language (Qt) | Desktop, Mobile, Embedded | Calls registered C++ methods exposed through `Q_INVOKABLE` or public slots. [Qt Qml licensing](https://doc.qt.io/qt-6/qtqml-index.html). |
| [Slint](https://docs.slint.dev/latest/docs/slint/guide/getting-started/) | Declarative UI language | Desktop, Mobile, Embedded, Web/WASM | UI callbacks can invoke compiled Rust or C++ functions supplied by the host. [GPLv3 option](https://github.com/slint-ui/slint). |
| [Scratch (via TurboWarp)](https://docs.turbowarp.org/development/extensions/unsandboxed) | Visual programming (Scratch-compatible VM) | Desktop, Web | TurboWarp JavaScript extensions expose browser APIs as blocks. The [extension gallery](https://extensions.turbowarp.org/) includes GPU-accelerated Simple 3D. [Runtime source](https://github.com/TurboWarp/scratch-vm). |
| [Snap!](https://docs.snap.berkeley.edu/snap-manual.pdf) | Visual programming (JS engine) | Web | The `JavaScript function` block can wrap browser WebGL calls when JavaScript extensions are enabled. AGPL. |
| [Myra](https://github.com/tinyBigGAMES/MyraLang) | Native (via C++23) | Desktop | Allows inline C++ in Myra source, including calls to native functions. Apache 2.0. |
| [Sage (Adam McDaniel)](https://github.com/adam-mcdaniel/sage) | Native or managed VM | Desktop, Web | The FFI can call C functions or backend-specific host functions. The browser demo uses JavaScript callbacks for graphics. MIT. |
| [Nu](https://github.com/programming-nu/nu) | Interpreted | Desktop (macOS, Linux) | Lisp-style syntax sends messages to native Objective-C objects through the Objective-C runtime. Apache 2.0. |
| [Parenscript](https://parenscript.common-lisp.dev/reference.html) | JS engine | Web | Compiles Lisp-style syntax to JavaScript. Browser programs can call WebGL methods through JavaScript interop. [BSD-3-Clause](https://parenscript.common-lisp.dev/). |
| [SquiLu](https://github.com/mingodad/squilu) | Managed VM (Squirrel-derived) | Desktop | Provides native extensions and GUI bindings for FLTK. Includes a template for adding modules. |
| [Ganymede](https://github.com/tinyBigGAMES/Ganymede) | Native (x64 JIT) | Desktop (Windows) | Scripts call native DLL functions through `external` declarations or use functions registered by the host. Apache 2.0. |
| **Non-UI-centric languages** | | | |
| [Gleam](https://gleam.run/) | Managed VM (BEAM), JS engine | Desktop (BEAM), Web (JavaScript) | Statically typed functional language targeting Erlang and JavaScript. Great for multiplayer backend servers	Poor fit for client rendering/physics |
| [Chapel](https://chapel-lang.org/docs/technotes/extern.html) | Native | Desktop | C interoperability through `extern proc`, C headers, and inline C blocks. |
| [Pony](https://tutorial.ponylang.io/c-ffi/calling-c.html) | Native | Desktop | Native C FFI, with external function declarations and calls. |
| [Idris 2](https://idris2.readthedocs.io/en/latest/ffi/ffi.html) | Backend-dependent | Desktop | `%foreign` declarations, including C FFI on supporting backends. |
| [Mercury](https://www.mercurylang.org/information/doc-latest/mercury_user_guide/Foreign-language-interface.html) | Native (C backend) | Desktop | C native integration through `pragma foreign_proc` on C-targeting backends. |
| [Prolog](https://www.swi-prolog.org/pldoc/man?section=foreign) | Managed VM (SWI-Prolog) | Desktop | SWI-Prolog provides a C foreign-language interface. [SWI-Prolog: BSD-2-Clause](https://www.swi-prolog.org/license.html). |
| [J](https://www.jsoftware.com/docs/help802/dictionary/dx015.htm) | Interpreted | Desktop | `15!:0`, commonly named `cd`, calls native library functions. [GPLv3 option](https://github.com/jsoftware/jsource). |
| [Pure](https://agraef.github.io/pure-docs/pure.html) | JIT (LLVM) | Desktop | `extern` declarations call C functions; supports native-library integration. GPLv3+ interpreter; LGPLv3+ runtime/libraries. |
| [S-Lang](https://www.jedsoft.org/slang/doc/html/cslang-5.html) | Interpreted (embedded) | Desktop | C functions exposed as interpreter intrinsics and dynamically loaded modules. [Open-source license terms](https://www.jedsoft.org/slang/license.html). |
| [GNU Octave](https://docs.octave.org/latest/Calling-External-Code-from-Oct_002dFiles.html) | Interpreted | Desktop | Native `.oct` extensions integrate C, C++, and Fortran routines. [GPL](https://octave.org/about). |
| [Agda](https://agda.readthedocs.io/en/latest/language/foreign-function-interface.html) | JS engine (JavaScript backend) | Web | `COMPILE JS` pragmas can wrap browser WebGL calls. [MIT compiler](https://github.com/agda/agda/blob/master/Agda.cabal). |
| [AWK (GNU Awk)](https://www.gnu.org/software/gawk/manual/gawk.html#Dynamic-Extensions) | Interpreted | Desktop (CLI) | `@load` makes functions from compiled C extensions callable from scripts. |
| [Open Object Rexx (ooRexx)](https://www.oorexx.org/docs/pdf/rexxapi.pdf) | Interpreted | Desktop | The C/C++ extension API lets compiled packages register native routines and methods that Rexx code can call. [Source repository](https://github.com/ooRexx/ooRexx). |
| [Starlark](https://pkg.go.dev/go.starlark.net/starlark#NewBuiltin) | Interpreted (embedded) | Desktop (inside a Go host) | The Go implementation lets hosts expose native functions as built-ins for configuration or mod scripting. [Source repository](https://github.com/google/starlark-go). |
| [X10](https://x10-lang.org/documentation/practical-x10-programming/native-code-integration.html) | Native (C++ backend) or JVM | Desktop | Supports native code annotations, including C++ code on the native backend. Graphics integration would need bindings. [License](https://github.com/x10-lang/x10/blob/master/LICENSE.html). |
| [Faust](https://faustdoc.grame.fr/manual/syntax/) | Compiled DSP language | Desktop, Web/WASM | The `ffunction` primitive declares foreign functions on compatible compiler backends. Faust can be used for audio effects and synthesis. [Compiler license](https://github.com/grame-cncm/faust/blob/master-dev/COPYING.txt). |
| [ChucK](https://chuck.stanford.edu/extend/) | Managed VM | Desktop | Scripts can use compiled C++ extensions called chugins for audio processing and other native functionality. [Source and license](https://github.com/ccrma/chuck). |
| [SuperCollider](https://github.com/supercollider/supercollider/wiki/Plugins-development) | Managed VM with native audio server | Desktop | Synth definitions can use audio processing units implemented by compiled C++ server plugins. [Source and license](https://github.com/supercollider/supercollider). |
| [Csound](https://csound.com/csound-plugins.html) | Audio synthesis language (Csound runtime) | Desktop | Programs can use native plugins that add opcodes. A game or app can embed Csound through its [C API](https://csound.com/docs/api/). [Source and license](https://github.com/csound/csound). |
| [GLSL ES](https://registry.khronos.org/webgl/specs/latest/1.0/) | GPU shader language | Web (WebGL) | Runs WebGL vertex and fragment shaders. The host application handles input, game state, and draw calls. |
| [Ink](https://github.com/inkle/ink/blob/master/Documentation/RunningYourInk.md) | Interpreted (embedded runtime) | Desktop (inside a host) | Interactive narrative scripts can call functions registered by the host through `BindExternalFunction`. [MIT](https://github.com/inkle/ink). |
| [Jsonnet](https://jsonnet.org/ref/bindings.html) | Interpreted (embedded) | Desktop (inside a host) | Data templating language. Hosts can register native callbacks through the [C API](https://github.com/google/jsonnet/blob/master/include/libjsonnet.h). |
| [WebAssembly text format (WAT)](https://developer.mozilla.org/en-US/docs/WebAssembly/Guides/Understanding_the_text_format) | WASM engine | Web/WASM | Handwritten modules can import JavaScript functions that wrap WebGL calls. The browser host owns the graphics context. |
| [Rye](https://github.com/refaktor/rye) | Interpreted | Desktop (inside a Go host) | Go code can expose project-specific functions as [Rye built-ins](https://pkg.go.dev/github.com/refaktor/rye/projectbuiltins). |
| [Gentee](https://github.com/gentee/gentee/) | Managed VM (embedded) | Desktop (inside a Go host) | Automation language with a Go embedding interface for custom functions. |
| [ISPC](https://ispc.github.io/ispc.html) | Native (SIMD compiler) | Desktop | SIMD kernels can call native host functions declared with `extern "C"`. [Compiler and license](https://github.com/ispc/ispc). |
| [Gwion](https://gwion.github.io/Gwion/Contributing/Plugins/introduction.html) | Audio scripting runtime | Desktop | Compiled C plugins register functions and types callable from audio scripts. [GPLv3](https://github.com/Gwion/Gwion). |
| [Cmajor](https://cmajor.dev/docs/LanguageReference) | Compiled DSP language | Desktop (inside a host) | A native host can supply external functions as C function pointers. [GPLv3 or later option](https://cmajor.dev/docs/Licence). |
| [Risor](https://risor.io/docs/go-embedding) | Managed VM (embedded) | Desktop (inside a Go host) | Automation and embedding language. A Go host can expose functions to scripts. [Apache 2.0](https://github.com/risor-io/risor). |
| [Emacs Lisp](https://www.gnu.org/software/emacs/manual/html_node/elisp/Loading.html) | Emacs runtime | Desktop (inside Emacs) | The GNU Emacs implementation can load native dynamic modules that expose callable Lisp functions. It is mainly used to extend the editor. |
| [GNU APL](https://www.gnu.org/software/apl/) | Interpreted | Desktop | GNU APL can bind native extension libraries as callable functions using `⎕FX`. Primarily used for array programming. [Native function example](https://lists.gnu.org/archive/html/bug-apl/2014-05/msg00074.html). |
| [SystemVerilog](https://verilator.org/guide/latest/connecting.html) | Hardware description and verification | Simulation (Verilator) | `import "DPI-C"` makes native C or C++ functions callable during simulation. [Simulator license](https://github.com/verilator/verilator/blob/master/LICENSE). |
| [VHDL](https://ghdl.github.io/ghdl-cosim/vhpidirect/declarations.html) | Hardware description | Simulation (GHDL) | GHDL's `VHPIDIRECT` interface binds VHDL subprograms to native code. [Source and license](https://github.com/ghdl/ghdl). |
| [Bluespec SystemVerilog](https://bluespec.dev/user-guide/designing-with-bluespec-link.html) | Hardware description and synthesis | Simulation (BSC) | `import "BDPI"` connects Bluespec code to C functions in simulation. [Open-source compiler](https://github.com/B-Lang-org/bsc). |
| [Modelica](https://specification.modelica.org/maint/3.6/functions.html) | Systems modeling and simulation | Desktop (OpenModelica) | External function declarations can call C functions from models. [OpenModelica](https://github.com/OpenModelica/OpenModelica). |
| [Blech](https://www.blech-lang.org/docs/user-manual/declarations/) | Reactive embedded systems language | Embedded, Desktop | External declarations can bind C functions and variables. [Apache 2.0 compiler](https://github.com/boschresearch/blech). |
| [occam-pi](https://www.cs.kent.ac.uk/projects/ofa/kroc/cif.html) | Concurrent systems language | Desktop (KRoC) | KRoC provides a C interface for calling native code from processes. [Open-source compiler](https://github.com/concurrency/kroc). |
| [Boo](https://github.com/boo-lang/boo/wiki/Invoke-Native-Methods-with-DllImport) | Managed VM (.NET) | Desktop | Uses .NET P/Invoke through `DllImport` to call native libraries. [BSD-licensed compiler](https://github.com/boo-lang/boo). |
| [Vim script](https://vimhelp.org/builtin.txt.html#libcall%28%29) | Interpreted (Vim) | Desktop (inside Vim) | In Vim builds with `+libcall`, `libcall()` and `libcallnr()` call functions in shared libraries. Primarily used for editor scripting. [Vim source and license](https://github.com/vim/vim). |
| [Scilab](https://help.scilab.org/docs/2025.1.0/en_US/link.html) | Interpreted | Desktop | `link` loads compiled C or Fortran routines for use from numerical scripts. [Source and license](https://gitlab.com/scilab/scilab). |
| [Yorick](https://yorick.sourceforge.net/manual/yorick_96.php.html) | Interpreted | Desktop | A numerical language whose `extern` interface makes compiled routines callable from scripts. [BSD-licensed implementation](https://github.com/llnl/yorick). |
| [GNU Data Language (GDL)](https://github.com/gnudatalanguage/gdl) | Interpreted | Desktop | This open-source implementation of IDL supports `CALL_EXTERNAL` for functions in native shared libraries. Primarily used for scientific analysis. [Project change log](https://github.com/gnudatalanguage/gdl/blob/master/NEWS). |
| [Picat](https://picat-lang.org/download/picat_guide_html/picat_guide.html) | Managed VM | Desktop | Programs can call predicates written in C and compiled into the runtime. Primarily used for logic and constraint programming. MPL-licensed C sources. |
| [Qore](https://www.qoretechnologies.com/manual/qorus/latest/qore/lang/html/qore_modules.html) | Interpreted | Desktop, Server | Loads native C++ modules. Primarily used for application and service integration. [Open-source license options](https://qore.org/). |
| [ScriptBasic](https://scriptbasic.org/docs/ug/ug_24.html) | Interpreted | Desktop | Loads compiled C extensions as dynamic libraries. [LGPL interpreter](https://scriptbasic.org/docs/ug/ug_1.html). |
| [Hack](https://github.com/facebook/hhvm/wiki/Extension-API) | JIT (HHVM) | Server | The HHVM Native Interface exposes C++ functions to code running on HHVM. [Open-source runtime](https://github.com/facebook/hhvm). |
| [Wybe](https://github.com/pschachte/wybe/blob/master/WYBE.md) | Native (LLVM) | Desktop | Foreign calls can specify C as their target language. [MIT compiler](https://github.com/pschachte/wybe). |
| [Cogent](https://github.com/au-ts/cogent/blob/master/docs/quickstart.rst) | Native (via C) | Desktop | The compiler integrates hand-written C through its foreign interface. Designed for verified systems code. [Source and licenses](https://github.com/au-ts/cogent). |
| [Rex](https://github.com/peterkelly/rex) | Interpreted (embedded) | Desktop (inside a Rust host) | A Rust host can register synchronous or asynchronous native functions for workflow scripts. MIT. |
| [Mog](https://github.com/voltropy/mog) | Native (embedded JIT) | Desktop (inside a host) | The runtime lets embedding applications register callable host functions. MIT. |
| [PowerShell](https://learn.microsoft.com/en-us/powershell/module/microsoft.powershell.utility/add-type?view=powershell-7.5) | Managed shell (.NET) | Desktop (CLI) | `Add-Type` compiles managed wrappers that can call native libraries through P/Invoke. [MIT](https://github.com/PowerShell/PowerShell/blob/master/LICENSE.txt). |
| [Bash](https://www.gnu.org/software/bash/manual/bash.html#Bash-Builtins) | Interpreted shell | Desktop (CLI) | `enable -f` loads compiled shared objects that provide callable builtins on systems with dynamic loading. GNU GPL. |
| [Zsh](https://zsh.sourceforge.io/Doc/Release/Zsh-Modules.html) | Interpreted shell | Desktop (CLI) | `zmodload` loads native modules that can expose callable shell builtins and math functions. [Source and license](https://github.com/zsh-users/zsh). |
| [Gluon](https://github.com/gluon-lang/gluon) | Managed VM (embedded) | Desktop (inside a Rust host) | Rust hosts can pass compiled functions directly to scripts. |
| [Cieto](https://github.com/the0cp/cieto) | Managed VM (embedded) | Desktop (inside a C host) | An embedding application can register native C functions that Cieto scripts call. GPLv3. |
| [Zygo (zygomys)](https://github.com/glycerine/zygomys) | Interpreted (embedded) | Desktop (inside a Go host) | Calls compiled Go methods on host-provided Go values. BSD-2-Clause. |
| [Lisp Flavoured Erlang (LFE)](https://github.com/lfe/lfe) | Managed VM (BEAM) | Desktop, Server | LFE can call Erlang modules backed by native functions loaded through [Erlang NIFs](https://github.com/erlang/otp/blob/master/erts/doc/references/erl_nif.md). Apache 2.0 compiler. |
| [Curry](https://www.curry-lang.org/kics2/download/kics2-3.4.0-manual.pdf) | Native (via KiCS2 and GHC) | Desktop | KiCS2 implements Curry `external` operations with Haskell wrappers, which can use GHC's C FFI. [Compiler license](https://github.com/curry-language/kics2/blob/master/LICENSE). |
| [Godot Shading Language](https://docs.godotengine.org/en/stable/tutorials/shaders/shader_reference/shading_language.html) | GPU shader language (Godot) | Desktop, Web | Godot's [Compatibility renderer](https://docs.godotengine.org/en/stable/tutorials/rendering/renderers.html) supports WebGL 2 for web exports. [MIT engine](https://github.com/godotengine/godot/blob/master/LICENSE.txt). |
| [Asp](https://github.com/asplang/asp) | Managed VM (embedded) | Embedded (inside a C host) | Scripts call functions exposed by native application libraries. [Example C libraries](https://github.com/asplang/asp-lib). MIT. |
