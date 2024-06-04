import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class DoublyLinkedListDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoublyLinkedList<Object> list = new DoublyLinkedList<>();
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Выберите операцию:");
            System.out.println("1. Проверить, пуст ли список");
            System.out.println("2. Установить указатель в начало списка");
            System.out.println("3. Установить указатель в конец списка");
            System.out.println("4. Добавить элемент за указателем");
            System.out.println("5. Добавить элемент до указателя");
            System.out.println("6. Удалить элемент за указателем");
            System.out.println("7. Удалить элемент до указателя");
            System.out.println("8. Переместить указатель вправо");
            System.out.println("9. Переместить указатель влево");
            System.out.println("10. Обменять значения элементов до и за указателем");
            System.out.println("11. Вывести список на экран");
            System.out.println("12. Выход");

            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод. Пожалуйста, введите число.");
                scanner.nextLine(); // очистка буфера сканера
                continue;
            }

            switch (choice) {
                case 1:
                    clearConsole();
                    System.out.println("Список " + (list.isEmpty() ? "пуст\n" : "не пуст\n"));
                    break;
                case 2:
                    clearConsole();
                    list.setPointerToStart();
                    System.out.println("Указатель установлен в начало списка\n");
                    break;
                case 3:
                    clearConsole();
                    list.setPointerToEnd();
                    System.out.println("Указатель установлен в конец списка\n");
                    break;
                case 4:
                    clearConsole();
                   try {
                        System.out.println("Введите элемент для добавления за указателем:");
                        Object elementAfter = scanner.next();
                        list.addAfterPointer(elementAfter);
                        System.out.println("Элемент добавлен\n");
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    clearConsole();
                    System.out.println("Введите элемент для добавления до указателя:");
                    Object elementBefore = scanner.next();
                    list.addBeforePointer(elementBefore);
                    System.out.println("Элемент добавлен\n");
                    break;
                case 6:
                    clearConsole();
                    try {
                        list.removeAfterPointer();
                        System.out.println("Элемент удален\n");
                    } catch (NullPointerException e) {
                        System.out.println("Невозможно удалить элемент: список пуст или указатель находится в конце списка\n");
                    }
                    break;
                case 7:
                    clearConsole();
                    try {
                        list.removeBeforePointer();
                        System.out.println("Элемент удален\n");
                    } catch (NullPointerException e) {
                        System.out.println("Невозможно удалить элемент: список пуст или указатель находится в начале списка\n");
                    }
                    break;
                case 8:
                    clearConsole();
                    list.movePointerRight();
                    System.out.println("Указатель перемещен вправо\n");
                    break;
                case 9:
                    clearConsole();
                    list.movePointerLeft();
                    System.out.println("Указатель перемещен влево\n");
                    break;
                case 10:
                    clearConsole();
                    if (list.getSize() == 0){
                        System.out.println("Список пуст\n");
                    }
                    else{
                        list.swapBeforeAndAfter();
                    }
                    break;
                case 11:
                    clearConsole();
                    if (list.getSize() == 0){
                        System.out.println("Список пуст\n");
                    }
                    else{
                        list.displayList();
                    }
                    break;
                case 12:
                    isRunning = false;
                    System.out.println("Выход из программы\n");
                    break;
                default:
                    System.out.println("Некорректный выбор. Пожалуйста, выберите номер операции от 1 до 12.");
                    break;
            }
        }
        scanner.close();
    }
        // Метод отчистки консоли
        public static void clearConsole() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
}

class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private Node<T> pointer;
    private int size;

    // Конструктор
    public DoublyLinkedList() {
        head = null;
        tail = null;
        pointer = null;
        size = 0;
    }

    // Внутренний класс для узла
    private static class Node<T> {
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

    // 4. Добавить элемент за указателем
    public void addAfterPointer(T element) {
        Node<T> newNode = new Node<>(element);
        if (pointer == null && !isEmpty()) {
            throw new IllegalStateException("Указатель не установлен.");
        }
        else if (pointer == null) {
            head = newNode;
            tail = newNode;
        } 
        else {
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
    public void addBeforePointer(T element) {
        Node<T> newNode = new Node<>(element);
        if (pointer == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = pointer.prev;
            newNode.next = pointer;
            if (pointer.prev != null) {
                pointer.prev.next = newNode;
            } else {
                head = newNode;
            }
            pointer.prev = newNode;
        }
        size++;
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
            } else {
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
            } else {
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
        }
        else{
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