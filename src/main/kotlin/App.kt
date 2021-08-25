import dao.Dao
import dao.StudentDao
import model.Student
import kotlin.system.exitProcess

class App {
    companion object{
        private val dao : Dao<Student, String> = StudentDao()
        private lateinit var studentDao: StudentDao
        @JvmStatic
        fun main (args : Array<String>){
            App().startApp()
        }
    }

    fun startApp (){
        studentDao = StudentDao()
        printHeader()
        readLine()?.let {
            navigateToMenu(it)
        }
    }

    private fun printHeader(){
        println("""
            ================================
            Aplikasi Data Mahasiswa
            ================================
            1. Cetak Data Mahasiswa
            2. Tambah Data Mahasiswa
            3. Hapus Data Mahasiswa
            4. Keluar
            ================================
            Masukan Pilihan Anda (1,2,3,4)?
            ================================
        """.trimIndent())
    }

    private fun navigateToMenu(menu : String){
        when (menu){
            "1" -> {
                openMenuPrintStudent()
            }
            "2" -> {
                openMenuInsertStudent()
            }
            "3" -> {
                openMenuDeleteStudent()
            }
            "4 " -> {
                exitProcess(0)
            }else -> {
                println("Pilihan tidak ada")
            }
        }
        askToMainMenu()
    }

    private fun openMenuDeleteStudent() {
        println("===========================")
        println("Hapus data dengan nim = ")
        readLine()?.let {
            dao.deleteData(it)
        }
        println("===========================")
        println("Hapus Data Berhasil")
    }

    private fun openMenuInsertStudent() {
        println("===========================")
        println("Nama Mahasiswa = ")
        val name = readLine().orEmpty()
        println("NIM Mahasiswa = ")
        val nim = readLine().orEmpty()
        println("Jurusan Mahasiswa = ")
        val major = readLine().orEmpty()
        println("Kelas = ")
        val className = readLine().orEmpty()
        println("Nama Universitas = ")
        val univ = readLine().orEmpty()
        dao.addData(Student(name,nim,major,className,univ))
        println("===========================")
        println("Insert Data Berhasil")
    }

    private fun openMenuPrintStudent() {
        val students = dao.getData()
        var number = 1
        if (students.isNotEmpty()){
            students.forEachIndexed{index, student ->
                println("===========================")
                println("Mahasiswa ke-${index + 1}")
                println("===========================")
                println("Nama           : ${student.name}")
                println("NIM            : ${student.nim}")
                println("Jurusan        : ${student.major}")
                println("Kelas          : ${student.className}")
                println("Universitas    : ${student.university}")
            }
        }else{
            println("===========================")
            println("Tidak Ada Data")
            println("===========================")
        }
    }

    private fun askToMainMenu(){
        println("""
            ===========================
            Kembali ke menu utama (Y/N)
            ===========================
        """.trimIndent())
        if(readLine().equals("Y", ignoreCase = true)){
            navigateToMainMenu()
        }else{
            exitProcess(0)
        }
    }

    private fun navigateToMainMenu() {
        printHeader()
        readLine()?.let {
            navigateToMenu(it)
        }
    }
}