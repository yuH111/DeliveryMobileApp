package com.example.test109
//Bước 1: Tạo  data class Student có các thuộc tính id, name, age
//Bước 2: TẠo 1 danh sách các Student ( khởi tạo trước 10 Student)
//Bước 3: add 1 Student vào danh sách
//Bước 4: xoá Student tại vị trí thứ 3
//Bước 5: xoá Student với điều kiện name = “Khanh”
//Bước 6: lọc những Student có age < 16
//Bước 7: tìm kiếm 1 Student có  name = “Long”
//Bước 8: dùng vòng lặp for hoặc forEach để xuất ra danh sách Student
fun main() {
    // Khởi tạo MutableList 10 Student
    data class Student(var id: Int, var name: String?, var age: Int?)
    val students: MutableList<Student> = mutableListOf(
        Student(1, "Huy", 20),
        Student(2, "Hoang", 21),
        Student(3, "Toan", 13),
        Student(4, "Dung", 14),
        Student(5, "Khanh", 20),
        Student(6, "Vu", 23),
        Student(7, "Lam", 21),
        Student(8, "Hoai", 20),
        Student(9, "Long", 19),
        Student(10, "Khoa", 22)
    )

    println("Danh sach 10 sinh vien:")

    students.add(Student(11,"Tony",23))

    students.removeAt(3)

   val a = students.removeIf { it.name == "Khanh" }
println("Da xoa sinh vien voi ten $a")

    students.forEachIndexed { index,abc ->
        println("$abc $index")

    }


    students.filter { (it.age ?: 0) < 16 }.forEach {  println("") }
   val b = students.find { it.name == "Long" }
    println("nguoi toi tim kiem o id so ${b?.id}")
    students.forEachIndexed { index,abc ->
        println("$abc $index")

    }
    students.sortedBy { it.name }.forEach { println(it) }
    students.forEachIndexed { index,abc ->
        println("$abc $index")

    }
}
