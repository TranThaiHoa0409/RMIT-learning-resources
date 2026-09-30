# Flowchart Symbols for GitHub README

A collection of Unicode symbols that can be used to create flowcharts directly in Markdown files and GitHub README files.

## 1. Basic Arrows

| Symbol | Meaning       |
| ------ | ------------- |
| `→`    | Right         |
| `←`    | Left          |
| `↑`    | Up            |
| `↓`    | Down          |
| `↔`    | Bidirectional |
| `↕`    | Up and down   |
| `↗`    | Up-right      |
| `↘`    | Down-right    |
| `↙`    | Down-left     |
| `↖`    | Up-left       |

## 2. Flow Arrows

```text
→  ←  ↑  ↓
⟶  ⟵  ⟷
⇒  ⇐  ⇑  ⇓
⟹  ⟸
➜  ➝  ➞  ➟  ➠  ➤
```

### Common usage

```text
Start → Login → Dashboard → Logout → End
```

## 3. Branching Arrows

Useful for `if / else` or decision branches.

```text
├→
└→
```

Example:

```text
Input
  ↓
Validation
  ↓
Decision
├→ Yes → Process A
└→ No  → Process B
```

## 4. Box Drawing Symbols

These symbols are useful for creating boxes and connecting them together.

### Corners and Lines

```text
┌ ┐
└ ┘
─ │
├ ┤
┬ ┴
┼
```

### Example

```text
┌─────────┐
│  Start  │
└────┬────┘
     ↓
┌─────────┐
│  Login  │
└────┬────┘
     ↓
┌─────────┐
│Dashboard│
└─────────┘
```

## 5. Flowchart Example

```text
┌─────────┐
│  Start  │
└────┬────┘
     ↓
┌─────────┐
│  Login  │
└────┬────┘
     ↓
  ┌───────┐
  │Valid? │
  └───┬───┘
   Yes│ No
      │
   ┌──┴───┐
   ↓      ↓
┌──────┐ ┌──────┐
│ Home │ │Retry │
└──────┘ └──┬───┘
            │
            └────→ Login
```

## 6. Useful Status Symbols

```text
✓   Success / Completed
✗   Failed / Error
✔   Confirmed
✘   Rejected
!   Warning
?   Decision / Question
⚠   Warning
ℹ   Information
```

Example:

```text
Login
  ↓
? Valid credentials?
  ├→ Yes → ✓ Success → Dashboard
  └→ No  → ✗ Error   → Retry
```

## 7. Common Flowchart Characters

```text
Start / End
┌───────────┐
│   Start   │
└───────────┘

Process
┌───────────┐
│  Process  │
└───────────┘

Decision
   ┌───────┐
   │ ?     │
   └───────┘

Input / Output
┌─────────────┐
│ Input / Out │
└─────────────┘
```

## 8. Recommended Symbols

For simple GitHub README flowcharts, these are usually enough:

```text
→   Main flow
↓   Downward flow
←   Backward flow
├→  Branch
└→  Final branch
↔   Two-way connection
?   Decision
✓   Success
✗   Error
⚠   Warning
```

## 9. Complete Example

```text
┌──────────┐
│  START   │
└────┬─────┘
     ↓
┌──────────┐
│  LOGIN   │
└────┬─────┘
     ↓
┌──────────┐
│ Valid? ? │
└────┬─────┘
     │
 ┌───┴────────┐
 │            │
Yes          No
 │            │
 ↓            ↓
✓ Success   ✗ Error
 │            │
 ↓            ↓
Dashboard   Retry
 │            │
 ↓            │
 Logout ──────┘
   ↓
  END
```

## 10. Quick Copy-Paste Set

```text
→ ← ↑ ↓ ↔ ↕
↗ ↘ ↙ ↖
⇒ ⇐ ⇑ ⇓
⟶ ⟵ ⟷
➜ ➝ ➞ ➟ ➠ ➤

├→ └→
┌ ┐ └ ┘
─ │ ├ ┤ ┬ ┴ ┼

✓ ✔
✗ ✘
? !
⚠ ℹ
```
