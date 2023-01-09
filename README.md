# hse-kpo-hw2

## Author, Group, University

Rakhimova Kamila, BSE214, HSE

## Topic

Here you can see completed individual homework 2.

## Task condition (ru)

Имеется корневая папка. В этой папке могут находиться текстовые файлы, а также другие папки. В других папках также могут находиться текстовые файлы и папки (уровень вложенности может оказаться любым).

В каждом файле может быть ни одной, одна или несколько директив формата: 

require ‘<путь к другому файлу от корневого каталога>’

Директива означает, что текущий файл зависит от другого указанного файла.

Необходимо выявить все зависимости между файлами, построить сортированный список, для которого выполняется условие: если файл А, зависит от файла В, то файл А находится ниже файла В в списке.

Осуществить конкатенацию файлов в соответствии со списком. Если такой список построить невозможно (существует циклическая зависимость), программа должна вывести соответствующее сообщение.

Найти все текстовые файлы, отсортировать их по имени и склеить содержимое в один текстовый файл.

## Structure

The solution was built on 4 classes:

- [Main](https://github.com/kamilarakhimova/hse-kpo-hw2/blob/main/Main.java) is used to run the program
- [FileHandler](https://github.com/kamilarakhimova/hse-kpo-hw2/blob/main/FileHandler.java) is used to get and handle files in the root directory
- [Checker](https://github.com/kamilarakhimova/hse-kpo-hw2/blob/main/Checker.java) is used to check files, folders, paths and etc. for correctness
- [UserInteraction](https://github.com/kamilarakhimova/hse-kpo-hw2/blob/main/UserInteraction.java) is used to interact with user and output various messages

Also you can find test example in folder [BasicExample](https://github.com/kamilarakhimova/hse-kpo-hw2/blob/main/BasicExample).

## Expected score

The task was completed with an expectation of getting a score 10/10.

## Two good boys smile

<img width="743" alt="Снимок экрана 2023-01-09 в 04 45 06" src="https://user-images.githubusercontent.com/58568615/211229622-1ee3f3f2-5674-4f42-a8af-44e7afe3b07c.png">

## The end!
