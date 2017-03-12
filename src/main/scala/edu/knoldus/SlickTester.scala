package edu.knoldus

import edu.knoldus.repositories._

import scala.concurrent.ExecutionContext.Implicits.global

object SlickTester {
  def main(args: Array[String]): Unit = {
    EmployeeRepo.create
    Thread.sleep(10000)
    val insertRes = EmployeeRepo.insert(Employee(10, "Gitika", 1.0))
    val res = insertRes.map { res => s"$res row inserted" }.recover {
      case ex: Throwable => ex.getMessage
    }
    val insertRes1 = EmployeeRepo.insert(Employee(11, "Raman", 4.0))
    val res1 = insertRes1.map { res1 => s"$res1 row inserted" }.recover {
      case ex: Throwable => ex.getMessage
    }
    val insertRes2 = EmployeeRepo.insert(Employee(12, "Anuj", 5.0))
    val insertRes3 = EmployeeRepo.insert(Employee(13, "Jatin", 4.0))
    val insertRes4 = EmployeeRepo.insert(Employee(14, "Anmol", 2.0))
    val insertRes5 = EmployeeRepo.insert(Employee(15, "Simar", 1.0))
    res.map(println(_))
    res1.map(println(_))
    Thread.sleep(10000)
    ProjectRepo.create()
    Thread.sleep(10000)
    val insertRes6 = ProjectRepo.insert(Project(1, "Logam", 10, 5, "Prashant"))
    val insertRes7 = ProjectRepo.insert(Project(2, "BankOfAmerica", 11, 6, "Ayush"))

    val res3 = insertRes6.map { res3 => s"$res3 row inserted" }.recover {
      case ex: Throwable => ex.getMessage
    }

    val res4 = insertRes7.map { res4 => s"$res4 row inserted" }.recover {
      case ex: Throwable => ex.getMessage
    }

    res3.map(println(_))
    res4.map(println(_))
    Thread.sleep(10000)

    DependentRepo.create()
    Thread.sleep(10000)
    val insertRes9 = DependentRepo.insert(Dependent(1, 10, "Amit", "brother", Some(20)))
    val insertRes10 = DependentRepo.insert(Dependent(2, 12, "Jyoti", "sister", Some(26)))

    val res5 = insertRes9.map { res5 => s"$res5 row inserted" }.recover {
      case ex: Throwable => ex.getMessage
    }
    val insertRes11 = DependentRepo.insert(Dependent(3, 11, "Aman", "brother", None))
    val res6 = insertRes11.map { res6 => s"$res6 row inserted" }.recover {
      case ex: Throwable => ex.getMessage
    }
    res5.map(println(_))
    res6.map(println(_))
    Thread.sleep(10000)

    /*val deletedRecord = EmployeeRepo.delete(4.0)
    val deleteResult = deletedRecord.map { deleteResult => s"$deleteResult row deleted" }.recover{
      case ex: Throwable => s"Deletion Failed ${ex.getMessage}"
    }*/

    /*val updatedRecord = EmployeeRepo.updateName(15, "Akhil")
    val updateResult = updatedRecord.map { updateResult => s"$updateResult row updated" }.recover {
      case ex: Throwable => s"Update Failed ${ex.getMessage}"
    }*/

    /*val upsertRecord = EmployeeRepo.upsert(Employee(15,"Akash",3.0))
    val upsertResult = upsertRecord.map { upsertResult => s"$upsertResult row updated/inserted" }.recover{
      case ex: Throwable => s"Upsert Failed ${ex.getMessage}"
    }
    Thread.sleep(10000)*/

    /*val updateTupleRecord = EmployeeRepo.updateTuple(12,("Amit",2.0))
    val updateTupleResult = updateTupleRecord.map { updateTupleResult => s"$updateTupleResult row updated" }.recover{
      case ex: Throwable => s"Update Tuple Failed ${ex.getMessage}"
    }*/

    /*val employeeList = EmployeeRepo.getAll
    val empListResult = employeeList.map { empListResult => s"$empListResult"}.recover{
      case ex: Throwable => s"Listing Failed ${ex.getMessage}"
    }
    println(Await.result(employeeList, Duration.Inf))
    Thread.sleep(10000)*/

    /*val updatedRecord1 = ProjectRepo.updateName(2, "RoyalCarribean")
    val updateResult1 = updatedRecord1.map { updateResult1 => s"$updateResult1 row updated" }.recover {
      case ex: Throwable => s"Update Failed ${ex.getMessage}"
    }*/

    /*val updateTupleRecord1 = ProjectRepo.updateTuple(1,("EY",11,6,"Bhavya"))
    val updateTupleResult1 = updateTupleRecord1.map { updateTupleResult1 => s"$updateTupleResult1 row updated" }.recover{
      case ex: Throwable => s"Update Tuple Failed ${ex.getMessage}"
    }*/

    /*val deletedRecord1 = ProjectRepo.delete("RoyalCarribean")
    val deleteResult1= deletedRecord1.map { deleteResult1 => s"$deleteResult1 row deleted" }.recover{
      case ex: Throwable => s"Deletion Failed ${ex.getMessage}"
    }*/

    /*val upsertRecord1 = ProjectRepo.upsert(Project(3,"RoyalCarribean",14,6,"Himanshu"))
    val upsertResult1 = upsertRecord1.map { upsertResult1 => s"$upsertResult1 row updated/inserted" }.recover{
      case ex: Throwable => s"Upsert Failed ${ex.getMessage}"
    }
    Thread.sleep(10000)*/

    /*val projectList = ProjectRepo.getAll
    val projListResult = projectList.map { projListResult => s"$projListResult"}.recover{
      case ex: Throwable => s"Listing Failed ${ex.getMessage}"
    }
    println(Await.result(projectList, Duration.Inf))
    Thread.sleep(10000)*/

    /*val updatedRecord2 = DependentRepo.updateName(2,"Sakshi")
    val updateResult2 = updatedRecord2.map { updateResult2 => s"$updateResult2 row updated" }.recover {
      case ex: Throwable => s"Update Failed ${ex.getMessage}"
    }*/

    /*val updateTupleRecord2 = DependentRepo.updateTuple(3,("Akash",11,"brother",Some(24)))
    val updateTupleResult2 = updateTupleRecord2.map { updateTupleResult2 => s"$updateTupleResult2 row updated" }.recover{
      case ex: Throwable => s"Update Tuple Failed ${ex.getMessage}"
    }*/

    /*val deletedRecord2 = DependentRepo.delete(12)
    val deleteResult2= deletedRecord2.map { deleteResult2 => s"$deleteResult2 row deleted" }.recover{
      case ex: Throwable => s"Deletion Failed ${ex.getMessage}"
    }*/

    /*val upsertRecord2 = DependentRepo.upsert(Dependent(4,15,"Ankita","mother",Some(50)))
    val upsertResult2 = upsertRecord2.map { upsertResult2 => s"$upsertResult2 row updated/inserted" }.recover{
      case ex: Throwable => s"Upsert Failed ${ex.getMessage}"
    }
    Thread.sleep(10000)*/

    /*val dependentList = DependentRepo.getAll
    val depListResult = dependentList.map { depListResult => s"$depListResult"}.recover{
      case ex: Throwable => s"Listing Failed ${ex.getMessage}"
    }
    println(Await.result(dependentList, Duration.Inf))*/

    /*val depEmpList = DependentRepo.getDependentWithEmployee
    val depEmpListResult = depEmpList.map { depEmpListResult => s"$depEmpListResult"}.recover{
      case ex: Throwable => s"Listing Failed ${ex.getMessage}"
    }
    println(Await.result(depEmpList, Duration.Inf))*/

    /*val projEmpList = ProjectRepo.getProjectWithEmployee
    val projEmpListResult = projEmpList.map { projEmpListResult => s"$projEmpListResult"}.recover{
      case ex: Throwable => s"Listing Failed ${ex.getMessage}"
    }
    println(Await.result(projEmpList, Duration.Inf))*/

  }

}
