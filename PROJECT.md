# CareerReady — Project Plan

A personal desktop study app, built in Java and JavaFX, for learning and memorizing programming concepts through repetition.

---

## 1. Purpose

People learn something, forget it, and relearn it when they need it again. In tech this happens all the time because there is always more to study. CareerReady is meant to stop that loop by:

1. Keeping study notes in one organized place.
2. Turning those notes into things you can practice (flashcards and coding drills).
3. Bringing material back at the right time so it sticks.
4. Showing what you have actually memorized and what is starting to fade.

The first version is a **personal tool for my classes**. It focuses on two things:

- **Flashcards** for studying class material
- **Rewriting code from memory** to memorize Java syntax

It is also a **learning project**. Building it should improve my Java, JavaFX, and software engineering skills step by step.

### Decisions So Far

| Decision | Choice |
|---|---|
| Notes | Written **fresh** for this app in a fixed format, not imported from old notes |
| Main use | Studying for my classes |
| Card types | Start with **basic Q/A cards**, then add **fill-in-the-blank** |
| Repositories | **Two separate repos:** one for the app code and one for my notes, so the app can be shared without my notes |
| Writing notes | In an editor I already use (IntelliJ or VS Code); the app reads them. An in-app editor can come later |

---

## 2. Guiding Principles

- **Simple first.** Each phase should produce a working app I can use, even if it has few features.
- **Few technologies.** Plain Java plus JavaFX, and only add a library when it solves a real problem.
- **My notes stay mine.** Markdown files on disk remain the source of truth. The app reads them and does not lock them away.
- **Learn by building.** Choose clear code over clever code. Write small classes, give them good names, and test the core logic.
- **Grow gradually.** Design so new features can be added later, but don't build them before they're needed.
- **Shareable from the start.** No personal paths or notes in the app code. Anyone should be able to download the app and point it at their own notes folder.

---

## 3. Goals

| Goal | Description |
|---|---|
| Markdown notes | Import and organize existing `.md` study notes by **subject → topic** |
| GitHub sync | Access and sync study materials kept in a GitHub repository |
| Flashcards | Turn notes into question/answer cards for repetition |
| Coding practice | Rewrite Java code from memory and compare it to the original |
| Progress tracking | Completion %, correct/incorrect attempts, memorization progress |
| Memorization (future) | Spaced repetition that confirms a concept is really memorized and raises difficulty over time |
| Easy UI | A clean JavaFX desktop app that is simple to navigate |

---

## 4. Tech Stack (Initial)

Kept small on purpose:

| Area | Choice | Why |
|---|---|---|
| Language | **Java 21** (LTS, already installed) | Modern and stable, with records, `switch` expressions, and text blocks |
| UI | **JavaFX 21** | Desktop UI toolkit that works well with Java |
| Build tool | **Maven** | Manages JavaFX and other dependencies; well supported in IntelliJ |
| Layout | **FXML + Scene Builder** (optional) | Keeps UI layout separate from Java logic |
| Storage | **SQLite** via JDBC (`sqlite-jdbc`) | One local file with no server to run, and a good way to learn SQL |
| Markdown | **commonmark-java** | Parses Markdown and renders it to HTML for display |
| Testing | **JUnit 5** | Tests for core logic such as card parsing and scheduling |
| GitHub (Phase 3) | Local `git` clone first, then **JGit** if needed | Starts simple; the app can sync without a full API integration |

**Not in v1:** web servers, cloud databases, user accounts, Spring, Docker, or AI. These can come later if the app grows.

---

## 5. Core Features

### 5.1 Markdown Notes

- Choose a **notes folder** on disk (a clone of the separate notes repo).
- The app scans it and builds a tree from the folder structure. Each **class is a subject** and each file is a topic, numbered to keep the order of the course:

  ```
  careerready-notes/
  ├── Intro-to-Java/                    ← Subject (a class)
  │   ├── 01-Variables-and-Types.md     ← Topic
  │   ├── 02-Control-Flow.md
  │   └── 03-OOP-Basics.md
  └── Data-Structures/
      ├── 01-Arrays.md
      └── 02-Linked-Lists.md
  ```

- Every note follows the same template. The full rules are in **5.1.1 Note Format Spec** below.

  ````markdown
  # OOP Basics

  ## Big Idea
  A class is a blueprint; an object is one thing built from it.

  ## Key Terms
  - **Class**: a blueprint that defines fields and methods
  - **Object**: an instance of a class, made with `new`

  ## Notes
  Explanation in my own words, with short examples.

  ## Common Mistakes
  - Forgetting `new`: `Dog d;` only declares a variable, it makes no object

  ## Flashcards
  Q: What are the four pillars of OOP?
  A: Encapsulation, Inheritance, Polymorphism, Abstraction.

  B: A {{constructor}} has the same name as its class.

  ## Practice
  ### Write a Dog class with a private name, a constructor, and a getter
  ```java practice
  public class Dog {
      private String name;
      public Dog(String name) { this.name = name; }
      public String getName() { return name; }
  }
  ```
  ````

- Show notes rendered as Markdown (commonmark → HTML in a JavaFX `WebView`).
- Search across note titles and content (simple text search is enough at first).
- Re-scan the folder to pick up changes.

#### 5.1.1 Note Format Spec (v1)

The `.md` file is the source of truth. It must read well on GitHub, be editable by hand, and be safe for the app to read and write back later (a future in-app editor saves the same format). Progress data never goes in notes; it lives in SQLite.

**File and folder rules**
- Folder = subject (`Intro-to-Java/`). File = topic, numbered for course order (`03-OOP-Basics.md`).
- Line 1 is `# Topic Title`.

**Sections**: `##` headings, always in this order, spelled exactly like this. Any section may be empty or left out, but no other `##` headings are used.

| Section | What goes in it | What the app does with it |
|---|---|---|
| `## Big Idea` | 1–3 sentences, the 30-second version | Shown at the top of the note |
| `## Key Terms` | `- **Term**: definition`, one per line | Later: auto term/definition cards and multiple choice |
| `## Notes` | Free Markdown, in my own words | Displayed only, not parsed |
| `## Common Mistakes` | `- mistake: why / fix`, one per line | Displayed; later a review list |
| `## Flashcards` | `Q:`/`A:` and `B:` cards | Flashcard study (Phases 2–3) |
| `## Practice` | `###` prompt followed by a ` ```java practice ` block | Coding drills (Phase 4) |

**Card rules**
- `Q:` starts a question; the next `A:` starts its answer. An answer can span several lines.
- **A blank line ends a card.** No blank lines inside a card.
- `B:` is a one-line fill-in-the-blank card. Each `{{word}}` is a hidden answer.
- Once written, keep a card's question text the same. Progress is matched by question text, so changing it resets that card.

**Practice rules**
- Each drill is a `### Prompt` heading directly followed by one ` ```java practice ` block.
- The prompt is shown, and the code is hidden and used as the answer.

### 5.2 Flashcards

Cards are written **inside the notes** using a simple syntax, so the Markdown files hold everything:

```markdown
## Interfaces

Q: What is the difference between an abstract class and an interface?
A: An abstract class can have state (fields) and constructors; a class can
   extend only one. A class can implement many interfaces.

Q: Can an interface have method implementations?
A: Yes — `default` and `static` methods (Java 8+), and `private` methods (Java 9+).
```

- The parser finds `Q:` / `A:` pairs and links each card to its subject and topic.
- Study session: the question is shown, the user clicks **Show Answer**, then marks the card **Correct** or **Incorrect**.
- Study one topic, a whole subject, or "everything that's due."
- Cards can also be added or edited from inside the app; they are written back to the `.md` file (later phase).

#### Fill-in-the-Blank Cards (after basic cards work)

The hidden word goes inside `{{ }}`:

```markdown
B: The {{final}} keyword prevents a class from being extended.
B: A {{constructor}} has the same name as its class and no return type.
```

- The app shows the sentence with a blank: `The _____ keyword prevents a class from being extended.`
- v1: reveal the answer and self-grade, the same as Q/A cards.
- Later: type the missing word and the app checks it (ignoring case and extra spaces).

### 5.3 Coding Practice

Code drills are fenced code blocks marked with a tag:

````markdown
### Write a Singleton class with a lazy getInstance()
```java practice
public class Singleton {
    private static Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }
}
```
````

- The app shows the drill's `###` prompt and hides the code.
- The user types the code from memory into a text editor area.
- **Check** compares the user's code with the original:
  - **v1:** line-by-line comparison that ignores whitespace, with differences highlighted.
  - **Later:** compile the attempt with `javax.tools.JavaCompiler` to catch syntax errors, then optionally run it.
- Results (attempt count, % of lines matched) count toward progress.

### 5.4 Progress Tracking

Stored in SQLite:

- Per card: times seen, correct count, incorrect count, last reviewed, current box/level.
- Per drill: attempts, best match %, last attempt.
- Per topic/subject: **completion %** (cards studied at least once) and **mastery %** (cards in the top box).
- A simple **Dashboard** screen with totals, cards due today, a study streak, and the weakest topics.

### 5.5 GitHub Integration

Phased to keep it manageable:

There are **two repos**:

| Repo | Contains | Visibility |
|---|---|---|
| `CareerReady` | The app's source code, plus a small `sample-notes/` folder so new users can try it | Can be public and shared |
| `careerready-notes` | My own class notes, cards, and drills | Private or public, my choice |

1. **v1:** The user clones their notes repo with `git` and points the app at the folder. No integration code is needed.
2. **Next:** **Pull** and **Push** buttons that call `git` with `ProcessBuilder`, or use JGit.
3. **Later:** Show sync status (changes, ahead/behind) and commit edits made inside the app.

> Progress data (the SQLite file) stays local and separate from the notes repo, so notes stay clean.
> It can be backed up or synced separately later.

### 5.6 User Interface

A main window with a sidebar and a content area:

```
┌───────────────┬──────────────────────────────────────────┐
│  CareerReady  │                                          │
│               │                                          │
│  ▸ Dashboard  │           [ Current screen ]             │
│  ▸ Notes      │                                          │
│  ▸ Flashcards │                                          │
│  ▸ Practice   │                                          │
│  ▸ Progress   │                                          │
│  ▸ Settings   │                                          │
└───────────────┴──────────────────────────────────────────┘
```

- **Keyboard shortcuts** in study mode: `Space` = show answer, `1` = incorrect, `2` = correct.
- Light/dark theme using a CSS stylesheet (later).

---

## 6. Memorization System

### v1 — Leitner Boxes (simple to build and understand)

Every card starts in **Box 1**. A correct answer moves it up one box; an incorrect answer sends it back to Box 1.

| Box | Review again after |
|---|---|
| 1 | 1 day |
| 2 | 3 days |
| 3 | 7 days |
| 4 | 14 days |
| 5 | 30 days → counts as **"memorized"** |

### Later — Smarter Scheduling and Difficulty

- Switch to **SM-2** (the algorithm behind Anki) with an "ease" factor for each card.
- Answer ratings: *Again / Hard / Good / Easy*.
- **Difficulty levels** as a concept is mastered:
  1. Recognize: flip card (Q → A)
  2. Recall: type the answer
  3. Reverse: A → Q
  4. Apply: code drill using the concept
  5. Write from scratch: blank-page drill with only a prompt
- A concept counts as **truly memorized** only after it passes several reviews over increasing time gaps, at higher difficulty levels.

---

## 7. Suggested Project Structure

```
CareerReady/
├── pom.xml
├── PROJECT.md
└── src/
    ├── main/
    │   ├── java/com/careerready/
    │   │   ├── App.java                 ← JavaFX entry point
    │   │   ├── model/                   ← Subject, Topic, Note, Flashcard, CodeDrill, ReviewResult
    │   │   ├── parser/                  ← MarkdownNoteParser, FlashcardParser, DrillParser
    │   │   ├── repository/              ← Database access (SQLite / JDBC)
    │   │   ├── service/                 ← StudyService, SchedulerService, ProgressService, GitService
    │   │   └── ui/                      ← Controllers for each screen
    │   └── resources/com/careerready/
    │       ├── fxml/                    ← Screen layouts
    │       └── css/                     ← Styles
    └── test/java/com/careerready/       ← JUnit tests (parsers, scheduler)
```

**Layering rule:** `ui` → `service` → `repository` / `parser` → `model`. UI controllers contain no SQL or parsing logic. This keeps the code testable and teaches good structure.

---

## 8. Development Roadmap

Each phase ends with something usable.

### Phase 0 — Setup
- [ ] Convert the project to Maven with JavaFX dependencies
- [ ] "Hello JavaFX" window runs from IntelliJ
- [ ] Initialize git and push to GitHub

- [ ] Create the separate `careerready-notes` repo with a few example notes in the template format
- [ ] Add `sample-notes/` to the app repo

### Phase 1 — Notes Viewer
- [ ] Pick a notes folder (saved in settings)
- [ ] Subject/topic tree from the folder structure
- [ ] Render the selected note as Markdown

### Phase 2 — Basic Flashcards
- [ ] Parse `Q:` / `A:` cards from notes
- [ ] Study screen: show question → reveal → correct/incorrect
- [ ] Save results to SQLite
- [ ] JUnit tests for the parser

### Phase 3 — Fill-in-the-Blank Cards
- [ ] Parse `B:` cards with `{{answer}}` blanks
- [ ] Show the sentence with a blank, reveal, self-grade
- [ ] Optional: type the answer and have it checked automatically

### Phase 4 — Coding Practice
- [ ] Parse `java practice` blocks
- [ ] Typing screen with a line-by-line diff
- [ ] Track drill attempts and scores

### Phase 5 — Progress and Spaced Repetition
- [ ] Leitner box scheduling and "due today" queue
- [ ] Dashboard: completion %, mastery %, correct/incorrect totals
- [ ] Per-class and per-topic progress bars

### Phase 6 — GitHub Sync
- [ ] Pull/Push buttons
- [ ] Sync status indicator
- [ ] Edit and add cards in-app and write them back to Markdown

### Phase 7 — Smarter Memorization
- [ ] SM-2 scheduling with Again/Hard/Good/Easy
- [ ] Difficulty levels (recognize → recall → apply → write from scratch)
- [ ] Compile-check code drills with `javax.tools`
- [ ] Charts of progress over time (JavaFX `LineChart`)

---

## 9. Skills This Project Practices

- Java fundamentals: classes, records, enums, collections, streams, exceptions
- File I/O (`java.nio.file`) and text parsing
- JavaFX: layouts, controls, FXML, CSS, event handling, scene navigation
- SQL and JDBC
- Maven and dependency management
- Unit testing with JUnit
- Git/GitHub workflow
- Software design: layering, separation of concerns, small focused classes

---

## 10. Out of Scope for Now

To keep v1 achievable, these are **not** planned yet:

- User accounts, login, or multi-user support
- Cloud hosting or a web/mobile version
- Real-time collaboration
- Full GitHub REST API integration
- AI features of any kind

---

## 11. Long-Term Vision (Future Note)

> **This section is a direction for the far future, not part of the current build.**

The bigger idea behind CareerReady: **professionals in many fields learn, forget, and relearn.** Tech is one of the worst cases because skills keep changing and need constant study. Later on, CareerReady could become a tool for **anyone in any profession** who needs to keep important knowledge fresh and stay prepared.

### Possible future directions

- **Multi-profession support:** Not only programming, but also nursing, trades, law, finance, IT certifications, and more. Each field could have its own content packs.
- **Career-aware learning ("statistical AI"):** A system that analyzes job market data (job postings, required skills, industry trends) to find:
  - the **most in-demand knowledge today**, and
  - **emerging skills** that will matter tomorrow.

  It would then **prompt users to keep learning** what matters for their field, so they stay prepared instead of cramming when a need comes up.
- **Industry and company connection:**
  - Learning paths tied to specific **industries** or **job roles**
  - **Company-specific** training (onboarding, internal tools, compliance refreshers)
  - Possible partnerships where employers provide study content for their teams
- **Personalized readiness score:** "How prepared am I for a [role] position right now?", based on what the user has memorized compared with what the market is asking for.

### What this would eventually require (not now)

- A backend server and user accounts
- A source of job market data (public job posting APIs or datasets)
- Statistics/ML for skill trend analysis, possibly with AI to generate or suggest study material
- Privacy and data handling considerations

The first version's design leaves room for this: notes organized by **subject/topic**, and progress tracked per concept. Those pieces could later map onto "skills" and "job roles."
