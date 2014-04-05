package edu.unq.uis.planificador.environments

/**
 * Created by logain on 4/5/14.
 */
class DevEnvironment extends
UserServiceComponent with
UserRepositoryComponent with
org.specs.mock.JMocker
{
  val userRepository = mock(classOf[UserRepository])
  val userService = mock(classOf[UserService])
}