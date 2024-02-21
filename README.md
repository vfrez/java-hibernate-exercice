Start with:

Aplicar sql 

```sql
-- dados.DADOS_TABLE definition

CREATE TABLE `DADOS_TABLE` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `DADO_1` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INC
```

Rodar maven
```bash
   mvn clean install
   mvn compile exec:java -Dexec.mainClass="org.project.ManageDado"
```

Sem JPA
https://www.tutorialspoint.com/hibernate/hibernate_examples.htm

Com JPA
https://dzone.com/articles/persisting-entity-classes
https://thorben-janssen.com/mapping-definitions-jpa-hibernate-annotations-xml/