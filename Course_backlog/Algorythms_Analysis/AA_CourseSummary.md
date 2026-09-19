# Algorithms & Analysis — Course Summary (Weeks 1–10)

This is a consolidated summary of everything covered in the course so far, pulling together the ten weekly lecture notes (`AA_Week1_Introduction.md` through `AA_Week10_MoreAlgorithmicParadigms.md`). Use this as a quick-reference / revision map; refer back to the individual weekly files for full detail, diagrams, and worked examples.

---

## Course Structure

**Goals**
- Learn and apply common data structures and algorithms to solve computing problems
- Learn and apply problem-solving paradigms: brute force, divide and conquer, greedy, dynamic programming
- Evaluate algorithm complexity both theoretically and empirically

**Assessments**

| Assessment | Weight | Details |
|---|---|---|
| Weekly Quizzes | 10% | 48h window, 4 MCQs, open book, 10-minute duration |
| Midterm Test | 20% | Week 5, in-class, A4 cheat sheet, scope: lectures 1–4 |
| Mini Project | 30% | Week 9, individual |
| Final Test | 40% | Week 12, in-class, A4 cheat sheet, scope: all content |

---

## Week 1 — Introduction: Algorithms, ADTs, Data Structures

- **Algorithm**: unambiguous, finite, deterministic (usually) sequence of steps to solve a problem, independent of language.
- **Data abstraction (ADT)**: separates *what* operations do from *how* they're implemented. Examples: sets, sequences/lists, dictionaries/maps, stacks (LIFO), queues (FIFO), trees, graphs.
- **Prefix sum** introduced as a first example of trading precomputation/space for faster (O(1)) range queries, vs. the naive O(n)-per-query approach.
- **Trees**: connected acyclic graphs; binary tree = at most 2 children per node.
- **Graphs**: `G = {V, E}`; directed vs undirected; represented via adjacency matrix or adjacency list; can be weighted.
- **Core data structures**: array (contiguous, O(1) random access, fixed size) and linked list (nodes + pointers, flexible size). Both can be used to implement ADTs like stacks, queues, sets, and (with more work) trees/graphs.
- Python built-in types reviewed: numeric, bool, sequence types (list/tuple/range), str, set, dict, classes as custom ADTs.

---

## Week 2 — Algorithmic Complexity

- **Why compare algorithms**: performance differences become critical at scale (e.g., one-by-one search O(n) vs binary search O(log n)).
- **Basic operations**: the dominant operation(s) whose count determines running time, as a function of input size n.
- **Estimating cost**: consecutive statements add; if/else takes test + max(branches); loops multiply body cost by iterations; nested loops multiply loop sizes.
- **Best / Worst / Average case** — average is *not* the mean of best and worst; it's the expected cost over all inputs (needs probability reasoning).
- **Asymptotic complexity**: group running times into equivalence classes using bounds.
  - **Big-O O(g(n))** — upper bound.
  - **Big-Omega Ω(g(n))** — lower bound.
  - **Big-Theta Θ(g(n))** — tight bound (both upper and lower).
- **Common classes (best→worst)**: O(1), O(log n), O(n), O(n log n), O(n²), O(n³), NP, O(2ⁿ), O(n!).
- **Simplification rules**: drop low-order terms, drop constant multipliers, sum for sequential blocks, multiply for nested loops.
- **Recursion & recurrence relations**: e.g., factorial C(n) = C(n−1) + 1; solved via **backward substitution** to get a closed form (C(n) = n − 1 → O(n)).
- **Empirical analysis**: fit T(n) ≈ c·C(n) from measured data points; doubling/tripling input size reveals the growth class (2× → linear, 4× → quadratic, 8× → cubic, etc.).
- Theoretical vs empirical analysis: theory is implementation-independent but hides constants; empirical is realistic but implementation- and input-specific.

---

## Week 3 — Linear Data Structures

- **Arrays/Lists**: O(1) access, O(n) search/insert/delete (worst case), O(1) amortized append in Python (dynamic array, growth factor ≈1.125).
- **Linked Lists (singly)**: node = data + next pointer; head (and often tail) pointer.
  - Append/delete require care with the two-pointer technique (`previous`/`current`).
  - **Floyd's cycle detection ("Tortoise and Hare")**: O(n) time, O(1) space, to detect loops.
- **Doubly Linked Lists (DLL)**: nodes have both `prev` and `next`; insert/delete update up to 4 pointers.
- **Circular lists**: head and tail linked to form a circle, often with a dummy node, to simplify edge cases.
- **Complexity comparison table** (array vs SLL/DLL): arrays win on access (O(1) vs O(n)); linked lists win on insert/delete (O(1) vs O(n) once position is known).
- **Queues (FIFO)**: enQueue/deQueue/peek. Three implementation strategies discussed (plain list — O(n) deQueue; pointer/index-based; circular buffer with front/rear pointers). Priority queue introduced (later solved efficiently with a heap, Week 6).
- **Stacks (LIFO)**: push/pop/peek. Applications:
  - **Matching parentheses/brackets** using a stack.
  - **Infix / Prefix / Postfix** expression notations, and algorithms to evaluate postfix and convert infix → postfix (operator precedence + stack).

---

## Week 4 — Trees

- **Tree terminology**: root, path, path length, level, size, parent/ancestor, child/successor, height/depth, subtree, leaf.
- **Binary tree**: ≤2 children per node (left/right).
- **Traversals**:
  - Depth-first (recursive): **pre-order** (node→L→R), **in-order** (L→node→R), **post-order** (L→R→node).
  - Breadth-first (iterative, uses a queue): layer by layer.
  - Binary tree height h ≈ log(size) when balanced → O(log n) access.
- **Binary Search Tree (BST)**: left subtree keys < node < right subtree keys.
  - **Search**: O(h) — compare and recurse left/right.
  - **Insert**: find correct leaf position, attach new node.
  - **Delete**: 3 cases — leaf (detach), one child (splice), two children (replace with min of right subtree / max of left subtree, then delete that node).
- **Balanced trees**: height difference between left/right subtrees bounded → guarantees O(log n) operations.
  - **Complete binary tree**: height = log(size).
  - **AVL Tree** (Adelson-Velskii & Landis, 1962): balance factor ∈ {−1,0,1} at every node; rebalanced via **rotations** (single/double, left/right) after insert/delete; rebalancing is O(log n).
  - **Red-Black Tree** (Guibas & Sedgewick, 1978): approximately balanced via color rules (red/black nodes, no red-red parent-child, equal black-height on all root→leaf paths); requires less rebalancing than AVL → faster inserts/deletes, but slightly slower search than AVL.

---

## Week 5 — Graphs

- **Graph = (V, E)**: generalizes trees by allowing cycles, multiple paths, and disconnection.
- **Terminology**: directed vs undirected; degree (in-degree/out-degree for directed); weighted vs unweighted; path, simple path, cycle, acyclic; connected vs disconnected, connected components.
- **Representations**:
  - **Adjacency matrix**: O(V²) space, O(1) edge lookup — good for dense graphs.
  - **Adjacency list**: O(V+E) space, slower edge lookup — good for sparse graphs.
- **Traversal** (both O(V²) with a matrix, O(V+E) with a list):
  - **BFS**: queue-based, level-by-level, guarantees shortest path in unweighted graphs, produces a BFS tree.
  - **DFS**: recursion/stack-based, goes as deep as possible before backtracking, produces a DFS tree.
- **Applications**: connected components (BFS/DFS from a start vertex), cycle detection (DFS + parent tracking), bipartite checking (2-coloring via BFS/DFS), shortest path in unweighted graphs (BFS + parent reconstruction).

---

## Week 6 — More Data Structures: Hash Tables, Heaps, Union-Find

**Hash Tables**
- Idea: compress a large key universe U down to table size n via a **hash function** h: U → {0,…,n−1}.
- **Collisions** occur when h(u) = h(v) for u ≠ v. Resolution strategies:
  - **Separate chaining**: each slot holds a linked list; average O(1) per operation under uniform hashing.
  - **Open addressing**: linear probing (next free slot) or double hashing (second hash function for step size); deletion needs a special "DELETED" marker.
- Table resizing/rehashing needed when load factor grows too high.

**Binary Heaps**
- Complete binary tree satisfying heap-order property (max-heap: parent ≥ children).
- Stored as an array: `left(i)=2i+1`, `right(i)=2i+2`, `parent(i)=(i-1)/2`.
- Operations: insert (trickle up), remove root (move last element to root, trickle down), build-heap from array (O(n) tight bound, O(n log n) naive bound).
- Applications: priority queues, heapsort, Dijkstra/Prim efficient implementations.

**Union-Find (Disjoint Set)**
- Tracks dynamic connectivity between elements via `find` (root lookup) and `union` (merge sets).
- Naive version can degrade to O(n) per `find`.
- Optimizations: **path compression** (flatten tree during find) + **union by rank/size** (attach smaller tree under larger). Combined → amortized O(α(n)) ≈ O(1) in practice.

**Comparison**: hash table = fast key lookup; heap = fast min/max retrieval (priority queue); union-find = fast connectivity queries. All trade extra space for speed.

---

## Week 7 — Brute Force

- **Brute force**: solve directly from the problem definition, no cleverness — simple but often inefficient.
- **Selection Sort**: repeatedly select the smallest remaining element and swap into place. O(n²) comparisons, O(n) swaps/moves in all cases (input-insensitive). Not adaptive.
- **Bubble Sort**: repeatedly swap adjacent out-of-order elements, largest "bubbles" to the end each pass. O(n²) worst/average; improved (early-termination) version is O(n) best case if already sorted.
- **Stability**: a sort is stable if it preserves relative order of equal keys (matters e.g. for multi-key sorts).
- **Sequential Search**: O(n) worst case, O(n/2) average.
- **String Matching (brute force)**: slide pattern over text one position at a time; worst case O(nm), average O(n+m).
- **Convex Hull (brute force)**: check every pair of points as a candidate hull edge against all others → O(n³).
- **Exhaustive Search**: enumerate all candidate solutions, keep the best.
  - **Knapsack (0/1, brute force)**: check all 2ⁿ subsets → O(2ⁿ). **Pruning** discards infeasible branches early.
  - **Travelling Salesman Problem (TSP)**: check all permutations of cities → O(n!).
  - **8-Queens**: generate permutations of column placements, check validity.

---

## Week 8 — Divide and Conquer

- **Strategy**: divide problem into smaller subproblems, solve recursively, combine results.
- **Master Theorem**: for T(n) = aT(n/b) + f(n), let c = log_b(a):
  - Case 1: f(n) = O(n^p), p < c → T(n) = O(n^c)
  - Case 2: f(n) = O(n^c log^k n) → T(n) = O(n^c log^(k+1) n)
  - Case 3: f(n) = O(n^p), p > c, and regularity condition holds → T(n) = O(f(n))
  - Examples worked: binary search → O(log n); merge sort → O(n log n).
- **Merge Sort**: recursively split array in half down to single elements, then merge sorted halves back together. O(n log n) in all cases (input-insensitive), stable, but needs O(n) extra space.
- **Quick Sort**: choose a pivot, partition array around it (Lomuto — pivot at end; Hoare — pivot anywhere, more efficient swaps), recurse on partitions.
  - Best/average case O(n log n); worst case O(n²) (e.g., already-sorted array with poor pivot choice).
  - Not stable; typically faster in practice than merge sort due to lower constants and in-place operation.
  - Pivot selection strategies: first/last element, median-of-three, random.
- **Quickhull** (divide-and-conquer convex hull): find extreme points, recursively discard points inside the triangle formed, extend hull on each side. Worst case O(n²), average case O(n log n) — much better than the O(n³) brute-force version.

---

## Week 9 — Dynamic Programming

- **DP**: solve problems by combining solutions of **overlapping** subproblems, storing (memoizing) results to avoid recomputation — trades space for time.
- **DP vs Divide & Conquer**: D&C is best when subproblems are independent (e.g., merge sort); DP is best when subproblems overlap/repeat.
- **Two approaches**:
  - **Bottom-up**: iteratively fill a table from smallest to largest subproblem.
  - **Top-down (memoization)**: recurse normally, but cache/reuse already-computed results.
- **DP design steps**: define the state → write the recurrence/transition → identify base cases → decide computation order → read off the final answer.
- **1D DP examples**:
  - **Fibonacci**: `dp[i] = dp[i-1] + dp[i-2]`; O(n) time, O(n) space (optimizable to O(1)).
  - **Coin Change (min coins)**: `dp[x] = min(dp[x], dp[x-c]+1)` over each coin c; O(T×C) time, O(T) space.
- **2D DP examples**:
  - **0/1 Knapsack**: `V[i,j] = max(V[i-1,j], v_i + V[i-1,j-w_i])` if item fits, else `V[i-1,j]`. Θ(nW) time/space — **pseudo-polynomial** (depends on the magnitude of W, not just n).
    - **Backtrace** through the table to recover which items were chosen.
    - **Top-down memoized version (MFKnapsack)**: only computes table cells actually needed by the recursion, can save work vs. full bottom-up table.
  - **Longest Common Subsequence (LCS)**: `dp[i][j] = dp[i-1][j-1]+1` if characters match, else `max(dp[i-1][j], dp[i][j-1])`.
- **When to use which**: bottom-up when most/all subproblems are needed; top-down when only a subset of subproblems is actually needed.

---

## Week 10 — More Algorithmic Paradigms: Greedy, MST, Shortest Path, Topological Sort

**Greedy Algorithms**
- Build a solution piece by piece, always taking the locally best choice. Doesn't always give an optimal result, but does for some problems.
- **Activity Selection**: sort by finish time, greedily pick the earliest-finishing compatible activity — provably optimal.

**Minimum Spanning Tree (MST)**
- Spanning tree: connected, acyclic subgraph covering all vertices. MST = spanning tree with minimum total edge weight.
- **Prim's Algorithm**: grow a tree one vertex at a time, always adding the cheapest edge connecting the tree to a new vertex (uses a min-priority queue / "frontier set").
  - Complexity: O(V²) with adjacency matrix; O((V+E) log V) with adjacency list + min-heap.
- Applications: network design (minimize cabling cost), approximating TSP.

**Shortest Path**
- **Dijkstra's Algorithm** (single-source shortest path, non-negative weights): maintain a set S of finalized vertices; repeatedly pick the unfinalized vertex with smallest tentative distance, add to S, relax its neighbors' distances.
  - Complexity: O(V²) with adjacency matrix; O((V+E) log V) with adjacency list + min-heap.
  - Guaranteed optimal.

**Topological Sort** (for DAGs)
- Orders vertices so that every edge u→v has u before v. Used for job/task scheduling with dependencies, prerequisite orderings.
- **DFS method**: push each vertex to a stack after all its descendants are processed; pop stack for the order.
- **Source Removal method** (Kahn's algorithm): repeatedly find a vertex with in-degree 0, remove it (and its edges) from the graph, append it to the result; use a queue to track ready (in-degree-0) vertices.
- Multiple valid topological orderings can exist for the same DAG.

---

## Big Picture: Complexity Cheat-Sheet

| Structure / Algorithm | Time Complexity (typical) |
|---|---|
| Array access / Linked list access | O(1) / O(n) |
| Binary search (sorted array) | O(log n) |
| BST search/insert/delete (balanced) | O(log n) |
| BST search/insert/delete (unbalanced, worst) | O(n) |
| Hash table search/insert/delete (average) | O(1) |
| Heap insert / extract-max/min | O(log n) |
| Union-Find (with path compression + union by rank) | ~O(1) amortized |
| Selection sort / Bubble sort | O(n²) |
| Merge sort | O(n log n) (all cases) |
| Quick sort | O(n log n) average, O(n²) worst |
| BFS / DFS (adjacency list) | O(V + E) |
| Prim's / Dijkstra's (heap + adjacency list) | O((V+E) log V) |
| 0/1 Knapsack brute force | O(2ⁿ) |
| 0/1 Knapsack DP | O(nW) (pseudo-polynomial) |
| TSP brute force | O(n!) |
| Convex hull brute force / Quickhull | O(n³) / O(n log n) average |

## Paradigms Recap

| Paradigm | Idea | Example Algorithms |
|---|---|---|
| Brute Force | Solve directly from the definition | Selection sort, bubble sort, sequential search, brute-force convex hull, exhaustive search (knapsack, TSP, 8-queens) |
| Divide & Conquer | Split → solve independent subproblems → combine | Merge sort, quick sort, quickhull, binary search |
| Dynamic Programming | Split → solve **overlapping** subproblems, cache results | Fibonacci, coin change, 0/1 knapsack, LCS |
| Greedy | Always take the locally optimal choice | Activity selection, Prim's MST, Dijkstra's shortest path |
