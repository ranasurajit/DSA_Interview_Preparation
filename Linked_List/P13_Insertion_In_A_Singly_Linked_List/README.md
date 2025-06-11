# Insertion in a Singly Linked List

## Problem Statement

You are given a singly linked list of `N` positive integers. Your task is to add a node having the value `VAL` at position `POS` in the linked list. Positions are 0-indexed, i.e.:

- **POS = 0**: insert at the head (making the new node the new head).
- **0 < POS ≤ N**: insert somewhere in the middle or at the tail (if POS = N).

Return the head of the updated linked list after insertion.

---

## Function Description

Complete the function `insertNode(head, VAL, POS)` where:

- `head`: pointer/reference to the head of the singly linked list.
- `VAL`: integer value of the new node to be inserted.
- `POS`: integer position at which to insert the new node.

Return the head of the modified list.

---

## Constraints

- `1 ≤ N ≤ ?` *(Not specified on page — assume moderate list sizes)*
- `1 ≤ VAL ≤ 10^9` *(assuming positive integer constraints)*
- `0 ≤ POS ≤ N`

Be sure to handle edge cases:

- Inserting at the very beginning.
- Inserting at the end.
- Maintaining the `next` pointers properly.

---

## Examples

**Example 1:**

```
Input:
  List: 1 → 2 → 3 → 4
  VAL = 5
  POS = 2

Step-by-step:
  Position 0 - value 1
  Position 1 - value 2

Insert new node (5) between nodes 2 and 3:

Result: 1 → 2 → 5 → 3 → 4
```

**Example 2:**

```
Input:
  List: 10 → 20 → 30
  VAL = 5
  POS = 0

Insert new node (5) at head:

Result: 5 → 10 → 20 → 30
```

**Example 3:**

```
Input:
  List: 7 → 14 → 21
  VAL = 28
  POS = 3

Insert at tail (end of the list):

Result: 7 → 14 → 21 → 28
```

---

