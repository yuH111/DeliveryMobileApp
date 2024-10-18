package com.example.test109
//A) Tạo lớp abstract Human// co thuoc tinh cung nhu cac hanh vi truu tuong
//có các hành vi trừu tượng speak , run
//các thuộc tính height, weight, age, id
//
//Tạo interface Salary với phương thức trừu tượng getSalary
//Tạo interface Tax với phương thức trừu tượng sendTax ( nộp thuế)
//Tạo interface Event với phương thức createEvent (tổ chức sự kiện)
//
//
//b) Tạo lớp Teacher kế thừa Human, implements Salary và Tax
//có các thuộc tính thêm subject (môn dạy)
//Các hành vi : teach, getInfo
//
//c) Tạo lớp HeadTeacher kế thừa Teacher
//Thêm thuộc tính headClass (lớp chủ nhiệm)
//Các hành vi: override lại teach, salary ( gấp đôi lương của teacher), tax (thêm 10% giá trị so với teacher)
//
//d) TẠo lớp President Hiệu trưởng kế thừa Teacher implement Event
//có các thuộc tính school ( trường chủ nhiệm)
//Ở createEvent dùng when để tạo các event: tuyển giáo viên, tuyển học sinh, tư vấn tuyển sinh
//
//E) Tạo lớp HumanManager quản lý nhân viên trường có 2 thuộc tính: president và teacherList
//addTeacher
//replacePresident
//removeTeacher
//findTeacherById
//getInfoAllTeacher
//kotlin

abstract class Human( // khai bao nhung cai chung chung k co tieu de va k biet chinh xac no la cai gi cu the
    private var height: Double,
    private var weight: Double,
    private var age: Int,
    private var id: String
) {
    abstract fun speak(): String
    abstract fun run(): String // k dinh nghia dc cu the ham nay se lam gi


    internal fun getHeight() = height // chi truy cap dc o modun nay chu k truy cap dc full
    internal fun setHeight(value: Double) {
        height = value
    }

    internal fun getWeight() = weight
    internal fun setWeight(value: Double) {
        weight = value
    }

    internal fun getAge() = age
    internal fun setAge(value: Int) {
        age = value
    }

    internal fun getId() = id
    internal fun setId(value: String) {
        id = value
    }
}


interface Salary { // support da ke thua dc nhieu class con abtract chi dc don ke thua
    fun getSalary(): Double
}
interface Tax {
    fun sendTax(): Double
}
interface Event {
    fun createEvent(whenEvent: String): String
}

 open class Teacher( // dung open de ke thua cac class // muon cac class khac ke thua duoc teacher thi phai dung open
    height: Double,
    weight: Double,
    age: Int,
    id: String,
    private var subject: String,
    private var salary: Double
) : Human(height, weight, age, id), Salary, Tax {

    override fun speak(): String {
        return "Teacher ${getId()} is speaking."
    }

    override fun run(): String {
        return "Teacher ${getId()} is running."// cho phép thằng con nạp chồng lên
    }

    open fun teach(): String {
        return "Teacher ${getId()} is teaching $subject."
    }

    override fun getSalary(): Double { // nạp chồng phương thức giống như việc
        return salary
    }

    override fun sendTax(): Double {
        return salary * 0.1
    }

    fun getInfo(): String {
        return "Teacher Info: ID: ${getId()}, Subject: $subject, Salary: $salary, Tax: ${sendTax()}"
    }

    internal fun getSubject() = subject
    internal fun setSubject(value: String) {
        subject = value
    }

    internal fun setSalary(value: Double) {
        salary = value
    }
}

class HeadTeacher(
    height: Double,
    weight: Double,
    age: Int,
    id: String,
    subject: String,
    salary: Double,

    private var headClass: String
) : Teacher(height, weight, age, id, subject, salary) {
    fun a(){
        super.getSalary() // từ khóa từ lớp con có thẻ gọi đến lớp cha
        getSalary() // goij hàm salary của chính nó là ở trong classs này
    }
    override fun teach(): String {
        super.teach()
        return "HeadTeacher ${getId()} is teaching ${getSubject()} to class $headClass."
    }

    override fun getSalary(): Double {
        return super.getSalary() * 2

    }

    override fun sendTax(): Double {
        return getSalary() * 0.11
    }

    fun getHeadTeacherInfo(): String {
        return "HeadTeacher Info: ID: ${getId()}, Subject: ${getSubject()}, HeadClass: $headClass, Salary: ${getSalary()}, Tax: ${sendTax()}"
    }

    internal fun getHeadClass() = headClass
    internal fun setHeadClass(value: String) {
        headClass = value
    }
}

class President(
    height: Double,
    weight: Double,
    age: Int,
    id: String,
    subject: String,
    salary: Double,
    private var school: String
) : Teacher(height, weight, age, id, subject, salary), Event {

    override fun createEvent(whenEvent: String): String {
        return when (whenEvent) {
           SchoolEvent.TUYENHOCSINh.nameEvent->{
               "aaaaaaaaaaaaaaaaa"
           }
            SchoolEvent.TUYENNHANVIEN.nameEvent->{
                "bbbbbbbbbbbb"
            }
            SchoolEvent.TUYENCAU.nameEvent->{
                "cccccccccc"
            }
            else -> "Unknown event."
        }
    }

    fun getPresidentInfo(): String {
        return "President Info: ID: ${getId()}, School: $school, Salary: ${getSalary()}, Tax: ${sendTax()}"
    }

    internal fun getSchool() = school
    internal fun setSchool(value: String) {
        school = value
    }
}

class HumanManager(
    private var president: President?,
    private val teacherList: MutableList<Teacher> = mutableListOf()
) {

    fun addTeacher(teacher: Teacher) {
        teacherList.add(teacher)
    }

    fun replacePresident(newPresident: President) {
        president = newPresident
    }

    fun removeTeacher(teacherId: String) {
        teacherList.removeIf { it.getId() == teacherId }
    }

    fun findTeacherById(teacherId: String): Teacher? {
        return teacherList.find { it.getId() == teacherId }
    }

    fun getInfoAllTeachers(): String {
        return teacherList.joinToString(separator = "\n") { it.getInfo() }
    }

    internal fun getPresident() = president
    internal fun setPresident(value: President?) {
        president = value
    }
}
enum class SchoolEvent(val nameEvent: String){ // note
    TUYENHOCSINh(" TUYEN HOC SINH"),
    TUYENNHANVIEN(" TUYENNHANVIEN"),
    TUYENCAU(" TUYENCAU"),
}
fun main() {
    val teacher1 = Teacher(1.75, 70.0, 30, "01", "Toan", 5000.0)
    val teacher2 = Teacher(1.65, 60.0, 28, "02", "Tieng Anh", 4500.0)
    val headTeacher = HeadTeacher(1.80, 75.0, 40, "QH01", "Vat ly", 6000.0, "12/9")
    val president = President(1.78, 80.0, 50, "QH02", "Lich su", 7000.0, "THPT Thanh Khe ")
    president.createEvent(SchoolEvent.TUYENNHANVIEN.nameEvent)
    val manager = HumanManager(president)
    manager.addTeacher(teacher1)
    manager.addTeacher(teacher2)
    manager.addTeacher(headTeacher)

    println(manager.getInfoAllTeachers())
    println("Thong tin: ${president.getPresidentInfo()}")

    val newPresident =
        President(1.82, 85.0, 55, "PC02", "Nghien cuu Sinh", 8000.0, "THPT Nguyen Thuong Hien")
    manager.replacePresident(newPresident)
    println("Thong tin moi: ${newPresident.getPresidentInfo()}")
}