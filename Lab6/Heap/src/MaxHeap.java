/** A class that implements the ADT maxheap by using an array. */
/**
 * MaxHeap represents a binary max heap data structure.
 * It implements the MaxHeapInterface and provides methods for adding, removing, and sorting elements in the heap.
 * The heap is implemented using an array and supports generic types that extend the Comparable interface.
 */
public final class MaxHeap<T extends Comparable<? super T>>
implements MaxHeapInterface<T>{

    private T[] heap; // Array of heap entries; ignore heap[0]
    private int lastIndex; // Index of last entry and number of entries
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    /**
     * Constructs a MaxHeap object with the given array of entries.
     * The array is copied to the data field of the MaxHeap object.
     * The MaxHeap is then created by reheapifying the elements.
     *
     * @param entries The array of entries to be used to construct the MaxHeap.
     */
    public MaxHeap(T[] entries){
        this(entries.length); // Call other constructor
        lastIndex = entries.length;
        // Assertion: integrityOK = true
        // Copy given array to data field
        for (int index = 0; index < entries.length; index++)
        heap[index + 1] = entries[index];
        // Create heap
        for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
        reheap(rootIndex);
    } // end constructor


    

        /**
         * Constructs a MaxHeap with the specified initial capacity.
         * If the initial capacity is smaller than the default capacity, the default capacity is used instead.
         *
         * @param initialCapacity the initial capacity of the heap
         */
        public MaxHeap(int initialCapacity) {
            // Is initialCapacity too small?
            if (initialCapacity < DEFAULT_CAPACITY)
                initialCapacity = DEFAULT_CAPACITY;
            else // Is initialCapacity too big?
                checkCapacity(initialCapacity);
            // The cast is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
            heap = tempHeap;
            lastIndex = 0;
            integrityOK = true;
        } // end constructor
    

    public MaxHeap(){
        this(DEFAULT_CAPACITY); // Call next constructor
    } // end default constructor 

    /**
     * Retrieves the maximum element from the heap.
     * 
     * @return the maximum element in the heap
     */
    public T getMax(){
        checkIntegrity();
        T root = null;
        if (!isEmpty())
            root = heap[1];
        return root;
    } // end getMax

    /**
     * Checks if the given capacity is within the maximum capacity limit.
     * 
     * @param capacity the capacity to be checked
     * @return true if the capacity is within the limit, false otherwise
     */
    public boolean checkCapacity(int capacity){
        return capacity <= MAX_CAPACITY;
    } // end checkCapacity

    /**
     * Checks the integrity of the MaxHeap object.
     * Throws a SecurityException if the array object is corrupt.
     */
    public void checkIntegrity(){
        if (!integrityOK)
            throw new SecurityException("Array object is corrupt.");
    } // end checkIntegrity

    public boolean isEmpty(){
        return lastIndex < 1;
    } // end isEmpty

    public int getSize(){
        return lastIndex;
    } // end getSize

    /**
     * Adds a new entry to the max heap.
     * 
     * @param newEntry The object to be added to the heap.
     */
    public void add(T newEntry){
        checkIntegrity(); // Ensure initialization of data fields
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while ( (parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0)
        {
        heap[newIndex] = heap[parentIndex];
        newIndex = parentIndex;
        parentIndex = newIndex / 2;
        } // end while
        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
    } // end add

    /**
     * Removes and returns the maximum element from the heap.
     * 
     * @return The maximum element in the heap.
     */
    public T removeMax(){
        checkIntegrity(); // Ensure initialization of data fields
        T root = null;
        if (!isEmpty()){
            root = heap[1]; // Return value
            heap[1] = heap[lastIndex]; // Form a semiheap
            lastIndex--; // Decrease size
            reheap(1); // Transform to a heap
        } // end if
        return root; 
    } // end removeMax

    /**
     * Reheaps the heap starting from the specified root index.
     * This method compares the root element with its children and swaps them if necessary,
     * ensuring that the heap property is maintained.
     *
     * @param rootIndex the index of the root element to start reheap from
     */
    private void reheap(int rootIndex){
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;
        while (!done && (leftChildIndex <= lastIndex) ){
            int largerChildIndex = leftChildIndex; // Assume larger
            int rightChildIndex = leftChildIndex + 1;
            if ( (rightChildIndex <= lastIndex) &&
                heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0){
                    largerChildIndex = rightChildIndex;
            } // end if
            if (orphan.compareTo(heap[largerChildIndex]) < 0){
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
            done = true;
        } // end while
        heap[rootIndex] = orphan;
    }

    /**
     * Ensures that the heap has enough capacity to accommodate additional elements.
     * If the current capacity is not sufficient, the capacity of the heap is doubled.
     */
    public void ensureCapacity(){
        if (lastIndex >= heap.length)
            checkCapacity(2 * heap.length); // Is capacity too small?
    } // end ensureCapacity

    /**
     * Removes the specified element from the heap.
     * If the element is found, it is replaced with the last element in the heap,
     * and the heap is reorganized to maintain the heap property.
     *
     * @param anEntry the element to be removed from the heap
     */
    public void remove(T anEntry){
        checkIntegrity();
        int index = 1;
        while (index <= lastIndex){
            if (heap[index].equals(anEntry)){
                heap[index] = heap[lastIndex];
                lastIndex--;
                reheap(index);
            } // end if
            else
            index++;
        } // end while
    } // end remove

    /**
     * Returns a string representation of the elements in the heap.
     *
     * @return a string representation of the elements in the heap
     */
    public String toString(){
        String result = "";
        for (int index = 1; index <= lastIndex; index++)
            result += heap[index] + " ";
        return result;
    } // end toString

    /**
     * Returns an array representation of the elements in the MaxHeap.
     * The order of the elements in the array is not guaranteed to be sorted.
     *
     * @return an array containing the elements in the MaxHeap
     */
    public T[] toArray(){
        checkIntegrity();
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Comparable[lastIndex + 1];
        for (int index = 0; index < lastIndex; index++)
            result[index] = heap[index + 1];
        return result;
    } // end toArray

    /**
     * Checks if the heap contains a specific element.
     * 
     * @param anEntry the element to be checked
     * @return true if the element is found in the heap, false otherwise
     */
    public boolean contains(T anEntry){
        boolean found = false;
        int index = 1;
        while (!found && (index <= lastIndex)){
            if (anEntry.equals(heap[index]))
                found = true;
            index++;
        } // end while
        return found;
    } // end contains

    /**
     * Returns the index of the first occurrence of the specified element in the heap.
     * If the element is not found, -1 is returned.
     *
     * @param anEntry the element to search for in the heap
     * @return the index of the element, or -1 if not found
     */
    public int getIndexOf(T anEntry){
        int where = -1;
        boolean found = false;
        int index = 1;
        while (!found && (index <= lastIndex)){
            if (anEntry.equals(heap[index])){
                found = true;
                where = index;
            } // end if
            index++;
        } // end while
        return where;
    } // end getIndexOf

    /**
     * Displays the elements of the MaxHeap.
     */
    public void display(){
        for (int index = 1; index <= lastIndex; index++)
            System.out.print(heap[index] + " ");
        System.out.println();
    } // end display

    /**
     * Swaps the elements at the specified indices in the heap array.
     *
     * @param firstIndex the index of the first element to be swapped
     * @param secondIndex the index of the second element to be swapped
     */
    public void swap(int firstIndex, int secondIndex){
        T temp = heap[firstIndex];
        heap[firstIndex] = heap[secondIndex];
        heap[secondIndex] = temp;
    } // end swap

    /**
     * Sorts the elements in the heap in ascending order using the heap sort algorithm.
     * This method assumes that the heap is already built.
     * The number of swaps performed during the sorting process is printed to the console.
     */
    public void heapSort(){
        checkIntegrity();
        int numberOfSwaps = 0;
        for (int index = lastIndex; index > 1; index--){
            swap(1, index);
            numberOfSwaps++;
            reheap(1);
        } // end for
        System.out.println("Number of swaps: " + numberOfSwaps);
    } // end heapSort

    /**
     * Changes the priority of an element at the specified index in the heap.
     * If the new priority is greater than the old priority, the element is moved up in the heap.
     * If the new priority is less than or equal to the old priority, the element is moved down in the heap.
     *
     * @param index The index of the element whose priority needs to be changed.
     * @param newEntry The new priority value for the element.
     */
    public void changePriority(int index, T newEntry){
        checkIntegrity();
        if ( (index >= 1) && (index <= lastIndex) ){
            T oldEntry = heap[index];
            heap[index] = newEntry;
            if (oldEntry.compareTo(newEntry) < 0)
                reheap(index);
            else
                reheap2(index, lastIndex);
        } // end if
    } // end changePriority

    /**
     * Sorts the elements in the heap in ascending order using the heap sort algorithm.
     * This method assumes that the heap is already built and valid.
     * The number of swaps performed during the sorting process is printed to the console.
     */
    public void heapSort2(){
        checkIntegrity();
        int numberOfSwaps = 0;
        for (int index = lastIndex; index > 1; index--){
            swap(1, index);
            numberOfSwaps++;
            reheap2(1, index - 1);
        } // end for
        System.out.println("Number of swaps: " + numberOfSwaps);
    } // end heapSort2


    /**
     * Reheaps the heap starting from the specified root index and going up to the last index.
     * This method is used to maintain the heap property after removing an element from the heap.
     * 
     * @param rootIndex The index of the root node from where the reheap process starts.
     * @param lastIndex The index of the last node in the heap.
     */
    private void reheap2(int rootIndex, int lastIndex){
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;
        while (!done && (leftChildIndex <= lastIndex) ){
            int largerChildIndex = leftChildIndex; // Assume larger
            int rightChildIndex = leftChildIndex + 1;
            if ( (rightChildIndex <= lastIndex) &&
                heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0){
                    largerChildIndex = rightChildIndex;
            } // end if
            if (orphan.compareTo(heap[largerChildIndex]) < 0){
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
            done = true;
        } // end while
        heap[rootIndex] = orphan;
    }

    /**
     * Sorts the elements in the heap using the heap sort algorithm.
     * This method rearranges the elements in the heap in ascending order.
     * It uses the reheap3 method to maintain the heap property during sorting.
     * The number of swaps performed during sorting is printed at the end.
     */
    public void heapSort3(){
        checkIntegrity();
        int numberOfSwaps = 0;
        for (int index = lastIndex / 2; index > 0; index--){
            reheap3(index, lastIndex);
        } // end for
        for (int index = lastIndex; index > 1; index--){
            swap(1, index);
            numberOfSwaps++;
            reheap3(1, index - 1);
        } // end for
        System.out.println("Number of swaps: " + numberOfSwaps);
    } // end heapSort3

    /**
     * Reheaps the heap starting from the specified root index to the specified last index.
     * This method compares the elements in the heap and rearranges them to maintain the heap property.
     * 
     * @param rootIndex The index of the root element to start reheap from.
     * @param lastIndex The index of the last element in the heap.
     */
    private void reheap3(int rootIndex, int lastIndex){
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;
        while (!done && (leftChildIndex <= lastIndex) ){
            int largerChildIndex = leftChildIndex; // Assume larger
            int rightChildIndex = leftChildIndex + 1;
            if ( (rightChildIndex <= lastIndex) &&
                heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0){
                    largerChildIndex = rightChildIndex;
            } // end if
            if (orphan.compareTo(heap[largerChildIndex]) < 0){
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
            done = true;
        } // end while
        heap[rootIndex] = orphan;
    }

    /**
     * Removes all elements from the MaxHeap.
     */
    public void clear(){
        checkIntegrity();
        while (lastIndex > -1){
            heap[lastIndex] = null;
            lastIndex--;
        } // end while
        lastIndex = 0;
    } // end clear
    

} // end MaxHeap

