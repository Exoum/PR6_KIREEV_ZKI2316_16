import java.util.Scanner;
import java.util.InputMismatchException;

public class DoublyLinkedListDemo {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean isRunning = true;

    System.out.println("Выберите тип данных для списка:");
    System.out.println("1. String");
    System.out.println("2. Integer");
    int dataTypeChoice = scanner.nextInt();
    scanner.nextLine(); // очистка буфера сканера

    DoublyLinkedList<?> list = dataTypeChoice == 1 ? new DoublyLinkedListString()  : new DoublyLinkedListInteger();

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
            if(dataTypeChoice == 1){
              System.out.println("Введите элемент для добавления за указателем:");
              String elementAfter = scanner.next();
              ((DoublyLinkedListString)list).addAfterPointer(elementAfter);
              System.out.println("Элемент добавлен\n");
            }
            else if(dataTypeChoice == 2) {
              System.out.println("Введите элемент для добавления за указателем:");
              if(scanner.hasNextInt()) {
                Integer elementAfter = scanner.nextInt();
                ((DoublyLinkedListInteger)list).addAfterPointer(elementAfter);
                System.out.println("Элемент добавлен\n");
              } else {
                System.out.println("Ошибка: введённый элемент не является числом.\n");
                scanner.next(); // очистка буфера сканера
              }
            }
          } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
          }
          break;
        case 5:
          clearConsole();
          if(dataTypeChoice == 1){
            System.out.println("Введите элемент для добавления до указателя:");
            String elementBefore = scanner.next();
            ((DoublyLinkedListString)list).addBeforePointer(elementBefore);
            System.out.println("Элемент добавлен\n");
          }
          else if(dataTypeChoice == 2){
            System.out.println("Введите элемент для добавления до указателя:");
            Integer elementBefore = scanner.nextInt();
            ((DoublyLinkedListInteger)list).addBeforePointer(elementBefore);
            System.out.println("Элемент добавлен\n");
          }
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