package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * @author kalpak
 *
 * Given a nested list of integers, implement an iterator to flatten it.
 *
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 * Input: [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,1,2,1,1].
 *
 * Example 2:
 * Input: [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,4,6].
 */


// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {

     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     public boolean isInteger();

     // @return the single integer that this NestedInteger holds, if it holds a single integer
     // Return null if this NestedInteger holds a nested list
     public Integer getInteger();

     // @return the nested list that this NestedInteger holds, if it holds a nested list
     // Return null if this NestedInteger holds a single integer
     public List<NestedInteger> getList();
 }

 public class FlattenNestedListIterator {
    Deque<NestedInteger> stack;

    // In the constructor, we push all the nestedList into the stack from back to front, so when we pop the stack, it returns the very first element.
    // Second, in the hasNext() function, we peek the first element in stack currently, and if it is an Integer, we will return true and pop the element. If it is a list, we will further flatten it.  Again, we need to iterate from the back to front of the list.

    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        stack = new ArrayDeque<>();
        flattenList(nestedList);

    }

    public Integer next() {
        if(hasNext())
            return stack.pop().getInteger();
        return null;
    }

    public boolean hasNext() {
        while(!stack.isEmpty()) {
            if(stack.peek().isInteger())
                return true;
            else {
                List<NestedInteger> tempList = stack.pop().getList();
                flattenList(tempList);
            }
        }
        return false;

    }

    private void flattenList(List<NestedInteger> nestedList) {
        for(int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }
}
