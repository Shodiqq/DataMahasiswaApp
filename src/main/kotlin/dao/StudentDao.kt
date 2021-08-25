package dao

import model.Student

class StudentDao : Dao<Student, String> {
    private var students = arrayListOf<Student>().apply {
        add(Student("Toni Stark","12345","Teknik Informatika","4IA01","Universitas Guna Dharma"))
        add(Student("Black Widdow","12346","Elektro","4IA02","Universitas Indonesia"))
        add(Student("Hawkeye bin Robin","12347","Teknik Komputer","4IA03","Universitas Pancasila"))
        add(Student("Bruce bin Banner","12348","Teknik Elektro","4IA04","IPB"))
    }
    override fun getData(): List<Student> {
        return students
    }

    override fun addData(item: Student) {
        students.add(item)
    }

    override fun deleteData(uniqueID: String) {
        students.remove(students.find { student -> student.nim == uniqueID })
    }
}