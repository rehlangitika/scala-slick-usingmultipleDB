package edu.knoldus.repositories

import edu.knoldus.connection.{DBComponent, MySqlComponent, PostgresComponent}

import scala.concurrent.Future

case class Dependent(id: Int, empId: Int, name: String, relation: String, age: Option[Int])

trait DependentTable extends EmployeeTable {

  this: DBComponent =>

  import driver.api._

  private[repositories] class DependentTable(tag: Tag) extends Table[Dependent](tag, "dependent") {

    val id = column[Int]("dependentid", O.PrimaryKey, O.AutoInc)
    val empId = column[Int]("empid")
    val name = column[String]("name")
    val relation = column[String]("relation")
    val age = column[Option[Int]]("age", O.Default(None))

    def employeeDependentFK = foreignKey("employee-dependent_fk", empId, employeeTableQuery)(_.id)

    def * = (id, empId, name, relation, age) <> (Dependent.tupled, Dependent.unapply)

  }

  protected val dependentTableQuery = TableQuery[DependentTable]

  protected def dependentTableAutoInc = dependentTableQuery returning dependentTableQuery.map(_.id)

}

trait DependentRepo extends DependentTable {

  this: DBComponent =>

  import driver.api._

  /*
  * creating schema for the given table
  * */

  def create(): Future[Unit] = db.run(dependentTableQuery.schema.create)

  /*
  * inserting a record
  * */

  def insert(dependent: Dependent): Future[Int] = db.run {
    dependentTableQuery += dependent
  }

  /*
  * deleting a record
  * */

  def delete(empid: Int): Future[Int] = {
    val query = dependentTableQuery.filter(d => d.empId === empid)
    val action = query.delete
    db.run(action)
  }

  /*
  * Updation of the name field of the record for the given id
  * */

  def updateName(id: Int, name: String): Future[Int] = {
    val query = dependentTableQuery.filter(_.id === id).map(_.name).update(name)
    db.run(query)
  }

  /*
  * Insertion(if record is not present) or updation(if existing record)
  * */

  def upsert(dependent: Dependent): Future[Int] = {
    val query = dependentTableQuery.insertOrUpdate(dependent)
    dependentTableQuery += dependent
    db.run(query)
  }

  /*
  * Retrieving all the records in the Table
  * */

  def getAll: Future[List[Dependent]] = db.run{
    dependentTableQuery.to[List].result
  }

  /*
  * Updation of tuple in the record with the given id
  * */

  def updateTuple(id: Int, values: (String, Int, String, Option[Int])): Future[Int] = {
    val query = dependentTableQuery.filter(_.id === id).map(d => (d.name, d.empId, d.relation, d.age)).update(values)
    db.run(query)
  }

  /*
  * Retrieving the Dependent details along with Employee details
  * */

  def getDependentWithEmployee: Future[List[(Employee, Dependent)]] = db.run {
    (for {
      record <- dependentTableQuery
      employee <- record.employeeDependentFK
    }yield (employee, record)).to[List].result
  }

}

object DependentRepo extends DependentRepo with MySqlComponent

