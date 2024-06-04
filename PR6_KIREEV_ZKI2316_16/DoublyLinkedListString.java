public class DoublyLinkedListString extends DoublyLinkedList<String>{
  // 4. Добавить элемент за указателем
  public void addAfterPointer(String data) {
    Node<String> newNode = new Node<>(data);
    if (pointer == null && !isEmpty()) {
      throw new IllegalStateException("Указатель не установлен.");
    }
    else if (pointer == null) {
      head = newNode;
      tail = newNode;
    } 
    else{
      newNode.next = pointer.next;
      newNode.prev = pointer;
      if (pointer.next != null) {
        pointer.next.prev = newNode;
      } else {
        tail = newNode;
      }
      pointer.next = newNode;
    }
    size++;
  }

  // 5. Добавить элемент до указателя
  public void addBeforePointer(String data) {
    Node<String> newNode = new Node<>(data);
    if (pointer == null) {
      head = newNode;
      tail = newNode;
    } 
    else {
      newNode.prev = pointer.prev;
      newNode.next = pointer;
      if (pointer.prev != null) {
        pointer.prev.next = newNode;
      } 
      else {
        head = newNode;
      }
      pointer.prev = newNode;
    }
    size++;
  }
}
