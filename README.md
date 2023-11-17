/**
* Spring Boot 2.1.2 + H2 Database + Swagger + DockerFile
* Author  : Mustafa Yoldaş
* Position: Software Developer - Full Stack
* Purpose : Sigorta Kampanya API POC Demo
*
*/
 
1.Proje kök dizininde terminalden Docker CLI kullanarak Docker image'ını oluşturabilirsiniz.

	docker build -t poc-demo . 

2.Docker image'ınızı kullanarak bir container oluşturun:

	docker run -p 8080:8080 poc-demo

3.Proje çalışma aşamasında data.sql içerisinde bulunan demo veriler otomatik eklenecektir.

4.Tarayıcıdan projeyi çalıştırmak için:

	http://localhost:8080/campaigns/view
