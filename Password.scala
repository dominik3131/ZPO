package Company

object Password {

  def isLongerThan (minLength:Int)(x:String) = {
    x.length()>=minLength
  }

  def isShorterThan (maxLength:Int)(x:String) = {
    x.length()<=maxLength
  }

  def isAnyUppercase(x:String) = {
    x.exists(_.isUpper)
  }

  def isAnyLowercase(x:String) = {
    x.exists(_.isLower)
  }

  def isAnyDigit(x:String) = {
    x.exists(_.isDigit)
  }

  def areTwoDigits(x:String) = {
    x.count(_.isDigit)>=2
  }

  def validate( x:String,validationRules: String=>Boolean*) = {
    validationRules.map(s=>s(x)).forall(_ == true)
  }

  def main(args: Array[String]) {

    print(validate("AlaMaKota12",isLongerThan(2)_,isShorterThan(15)_,isAnyUppercase _,isAnyLowercase _,isAnyDigit _,areTwoDigits _))


  }
}
