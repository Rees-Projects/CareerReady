# CareerReady — Progress & Time Sheet

**Current phase:** Phase 1 — Notes Viewer
**Next step:** Phase 1, show the notes folder as a tree in the JavaFX window

## Phase 0 Checklist

- [x] Initialize git and push to GitHub (`Rees-Projects/CareerReady`)
- [x] Convert project to Maven (`pom.xml`, standard folder layout)
- [x] Install Maven 3.9.9 (in `~/.local/opt`, linked from `~/.local/bin/mvn`)
- [x] Load the Maven project in IntelliJ
- [x] Write `App.java` and get the JavaFX window running
- [x] Add `.idea/` and `*.iml` to `.gitignore`
- [x] Create the separate `careerready-notes` repo with example notes
- [x] Add `sample-notes/` to the app repo

## Time Sheet

| Date | Hours | Work done |
|---|---|---|
| 2026-11-09 | ~0.7 (18:52–19:34) | Planned the project: wrote `PROJECT.md`, decided the notes format, card types, and the two-repo setup. Added `CLAUDE.md` rules and this file. Pushed to GitHub. Converted to Maven, installed Maven, loaded it in IntelliJ. |
| 2026-11-10 | ~1 | Wrote `App.java` skeleton (TODOs 1–5): learned Stage/Scene/Node, storing objects in variables, setters. Started `project-learnings/` notes folder. |
| 2026-11-12 | ~1 | Finished `App.java` (`launch(args)`), ran the window with `mvn javafx:run`. Added `.idea/` and `*.iml` to `.gitignore` and untracked old `.idea` files. Committed and pushed. |

## Phase Checklist

- [x] Phase 0 — Setup
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
- 2026-11-10: Learning notes go in `project-learnings/`, one numbered file per topic
- 2026-11-12: Notes format will be designed so a future in-app editor can save `.md` and push to the notes repo; Markdown stays the source of truth, progress stays in SQLite
- 2026-11-12: Note format spec v1 approved (PROJECT.md 5.1.1): Big Idea, Key Terms, Notes, Common Mistakes, Flashcards, Practice; a blank line ends a card; drill prompt is a `###` heading
