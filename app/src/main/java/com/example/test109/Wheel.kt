package com.example.test109
//a) Tạo 1 class Wheel ( bánh xe)
//Các thuộc tính là brand, width, thick
//Các hành vi setInfo, getInfo
//
//b) Tạo 1 class Car
//Các thuộc tính là color, brand, wheel, normalSpeed
//Các hành vi setInfo, getInfo, getMaxSpeed
//
//C) Tạo class TaxiCar kế thừa class Car
//Các thuộc tính là taxiBrand
//Các hành vi countTaxiCharge
//
//D) Tạo class GrabBar kế thừa   class Car
//Các thuộc tính là owner
//
//E) Tạo 1 class CarManager
//Quản lí các xe:
//gồm các phương thức Add 1 xe , Xoá 1 xe, lấy ra thông tin của xe..

class Wheel {
    private var brand: String = ""
    private var width: Double = 0.0
    private var thick: Double = 0.0

    // constructor() : this()//ham cua class la khi can wheel rong thi k can dung constructor

    internal fun setInfo(
        brand: String,
        width: Double,
        thick: Double
    ) { // internal thi chi truy cap dc trong modun nay chu k truy cap full
        this.brand = brand// chi ve thuoc tinh brand
        this.width = width
        this.thick = thick

    }

    internal fun getInfo(): String {
        return "Brand: $brand, Width = $width, Thick = $thick"

    }
}

open class Car { // giup cho 1 class co the ke thua
    private var color: String = ""
    private var brand: String = ""
    private var wheel: Wheel? = null
    private var normalSpeed: Double = 0.0
    internal fun setInfo(color: String, brand: String, wheel: Wheel, normalSpeed: Double) {
        this.color = color
        this.brand = brand
        this.wheel = wheel
        this.normalSpeed = normalSpeed

    }

    internal fun getInfo(): String {
        return "Color: $color, Brand = $brand, Wheel = $wheel, NormalSpeed = $normalSpeed"
    }


    class TaxiCar(): Car() {
      internal  fun countTaxiCharge(distance: Double, pricePerKm: Double): Double {
            print("Nhap vao so Km ban da di: ")
            val km = readLine()?.toDoubleOrNull() ?: 0.0
            if (km == 0.0) {
                println("Ban khong di taxi!")
                return 0.0
            }
            val a = pricePerKm * km
            println("So tien ban da di taxi la: $a")
            return a
        }
    }
}

class GrabBar(
    color: String,
    brand: String,
    wheel: Double,
    normalSpeed: Double,
    owner: String
) {

}



class Mananger {
    private val cars = mutableListOf<Car>() // khac voi list la list la danh sach tinh la val va k the them vot
        // mutable list la dong dung var co the sua danh sach
    internal fun addCar(car: Car) {// car đầu tiên để làm biến vd như mình dùng abc cũng đc
        cars.add(car)

    }

    internal fun removeCar(car: Car) {
        cars.remove(car)
    }

    internal fun getCarInfo(index: Int): String {
        return cars[index].getInfo()
    }
    internal  fun getAllCars(): List<Car>{
        return  cars
    }

}

fun main() {
    val wheel = Wheel()
    wheel.setInfo(thick = 2.3, width = 3.4, brand = "asas")
    val Car = Car()
    val taxiCar = Car()
    val mananger = Mananger()
    mananger.addCar(Car)
    mananger.addCar(taxiCar)
    println(mananger.getCarInfo(0))
    println(mananger.getCarInfo(1))


}
