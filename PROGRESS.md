# CareerReady — Progress & Time Sheet

**Current phase:** Phase 0 — Setup (in progress)
**Next step:** Write `App.java` (JavaFX hello window) and run it with `mvn javafx:run`

## Phase 0 Checklist

- [x] Initialize git and push to GitHub (`Rees-Projects/CareerReady`)
- [x] Convert project to Maven (`pom.xml`, standard folder layout)
- [x] Install Maven 3.9.9 (in `~/.local/opt`, linked from `~/.local/bin/mvn`)
- [x] Load the Maven project in IntelliJ
- [ ] Write `App.java` and get the JavaFX window running
- [ ] Add `.idea/` and `*.iml` to `.gitignore`
- [ ] Create the separate `careerready-notes` repo with example notes
- [ ] Add `sample-notes/` to the app repo

## Time Sheet

| Date | Hours | Work done |
|---|---|---|
| 2026-11-09 | ~0.7 (18:52–19:34) | Planned the project: wrote `PROJECT.md`, decided the notes format, card types, and the two-repo setup. Added `CLAUDE.md` rules and this file. Pushed to GitHub. Converted to Maven, installed Maven, loaded it in IntelliJ. |

## Phase Checklist

- [ ] Phase 0 — Setup
- [ ] Phase 1 — Notes Viewer
- [ ] Phase 2 — Basic Flashcards
- [ ] Phase 3 — Fill-in-the-Blank Cards
- [ ] Phase 4 — Coding Practice
- [ ] Phase 5 — Progress and Spaced Repetition
- [ ] Phase 6 — GitHub Sync
- [ ] Phase 7 — Smarter Memorization

## Decisions Log

- 2026-11-09: Notes written fresh in a fixed template (each class is a subject, each topic a numbered file)
- 2026-11-09: Cards start with Q/A, then fill-in-the-blank (`B:` with `{{answer}}`)
- 2026-11-09: Separate repos, `CareerReady` (app, shareable) and `careerready-notes` (my notes)
- 2026-11-09: Stack is Java 21, JavaFX, Maven, SQLite, commonmark-java, JUnit 5
