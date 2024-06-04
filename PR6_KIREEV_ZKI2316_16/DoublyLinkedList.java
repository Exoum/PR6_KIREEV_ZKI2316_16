import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {
  protected Node<T> head;
  protected Node<T> tail;
  protected Node<T> pointer;
  protected int size;

  // Конструктор
  public DoublyLinkedList() {
    head = null;
    tail = null;
    pointer = null;
    size = 0;
  }

  // Внутренний класс для узла
  protected static class Node<T> {
    T data;
    Node<T> prev;
    Node<T> next;

    Node(T data) {
      this.data = data;
      this.prev = null;
      this.next = null;
    }
  }

  // 1. Проверить, список пуст/не пуст
  public boolean isEmpty() {
    return size == 0;
  }

  // 2. Установить указатель в начало списка
  public void setPointerToStart() {
    pointer = head;
  }

  // 3. Установить указатель в конце списка
  public void setPointerToEnd() {
    pointer = tail;
  }

  // 6. Удалить элемент за указателем
  public void removeAfterPointer() {
    if (isEmpty()) {
      throw new NoSuchElementException("Список пуст.");
    } 
    else if (pointer == null || pointer.next == null) {
      throw new NoSuchElementException("За указателем нет элемента для удаления.");
    } 
    else if (pointer != null && pointer.next != null) {
      if (pointer.next.next != null) {
        pointer.next.next.prev = pointer;
      } 
      else {
        tail = pointer;
      }
      pointer.next = pointer.next.next;
      size--;
    }
  }

  // 7. Удалить элемент до указателя
  public void removeBeforePointer() {
    if (pointer != null && pointer.prev != null) {
      if (pointer.prev.prev != null) {
        pointer.prev.prev.next = pointer;
      } 
      else {
        head = pointer;
      }
      pointer.prev = pointer.prev.prev;
      size--;
    }
  }

  // 8. Переместить указатель вправо
  public void movePointerRight() {
    if (isEmpty()) {
      throw new NoSuchElementException("Список пуст.");
    } 
    else if (pointer == null || pointer.next == null) {
      throw new NoSuchElementException("Указатель уже находится в конце списка.");
    } 
    else if (pointer != null && pointer.next != null) {
      pointer = pointer.next;
    }
  }

  // 9. Переместить указатель влево
  public void movePointerLeft() {
    if (pointer != null && pointer.prev != null) {
      pointer = pointer.prev;
    }
  }

  // 10. Обменять значения элементов до указателя и за указателем
  public void swapBeforeAndAfter() {
    if (pointer != null && pointer.prev != null && pointer.next != null) {
      T temp = pointer.prev.data;
      pointer.prev.data = pointer.next.data;
      pointer.next.data = temp;
      System.out.println("Значения элементов обменяны\n");
    } else {
      System.out.println("Невозможно обменять элементы местами");
    }
  }

  // 11. Вывод списка на экран
  public void displayList() {
    Node<T> current = head;
    while (current != null) {
      System.out.print(current.data + " ");
      current = current.next;
    }
    System.out.println();
  }

  // Геттер для размера списка
  public int getSize() {
    return size;
  }
}
