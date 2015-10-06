# recommendation

Service REST renvoyant des recommendations d'hotel pour un lieu donné.

Composant web de la démo GoCD-Ansible. Pour plus d'information voir [cd-infrastructure](https://github.com/snicaise/cd-infrastructure)

# Usage

Prérequis : maven 3 et java 8

Build et tests
```sh
mvn clean package
```

Execution
```sh
cd recommendation-core
java -jar target/recommendation-core.jar server server.yml

curl -v http://localhost:8070/recommendation/londres
```

# Tests

Executer les tests unitaire :
```sh
mvn test
```

Executer les tests composant :
```sh
mvn test -Pcomponent-tests
```

Executer les tests d'intégration :
```sh
mvn test -Pintegration-tests
```

Executer tous les tests :
```sh
mvn test -Pall-tests
```
