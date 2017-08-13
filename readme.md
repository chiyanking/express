#项目结构
mvn archetype:generate -DgroupId=com.dagemen -DartifactId=express-parent -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
mvn archetype:generate -DgroupId=com.dagemen -DartifactId=express-domain -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
mvn archetype:generate -DgroupId=com.dagemen -DartifactId=express-dao -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
mvn archetype:generate -DgroupId=com.dagemen -DartifactId=express-service -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
mvn archetype:generate -DgroupId=com.dagemen -DartifactId=express-web -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false