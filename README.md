# 🛠️ Warranty Project – Spring Boot Cihaz Garanti Takip Uygulaması

Bu proje, bir iş başvurusunda verilen senaryoya dayalı olarak geliştirdiğim cihaz garanti kontrol ve yönetim sistemidir. Spring Boot, PostgreSQL ve REST mimarisi temel alınarak sıfırdan oluşturulmuştur. Daha önce hiç Spring Boot deneyimim olmamasına rağmen, yapay zeka destekli öğrenme, araştırma ve farklı teknolojilerle olan geçmiş tecrübelerim sayesinde bu projeyi başarıyla tamamladım.

## 📘 Proje Hakkında

**Warranty Project**, kullanıcıların cihazlarını sisteme kaydedebileceği, mevcut kayıtları görüntüleyebileceği, düzenleyebileceği veya silebileceği bir garanti takip uygulamasıdır. Aynı zamanda kullanıcılar, cihazların garanti durumunu da sistem üzerinden sorgulayabilir.

### 🔑 Temel Özellikler

- ✅ **Yeni Cihaz Ekleme:** Cihaz bilgileriyle birlikte garanti durumu girilerek kayıt oluşturulabilir.
- 🖊️ **Cihaz Düzenleme:** Var olan cihaz bilgileri güncellenebilir.
- 🗑️ **Silme İşlemi:** Gerekli durumlarda cihaz kaydı sistemden silinebilir.
- 🔍 **Garanti Sorgulama:** Seri numarası üzerinden garanti durumu hızlıca sorgulanabilir.
- 📋 **Cihaz Listesi:** Tüm kayıtlı cihazlar ve garanti durumları listelenebilir, düzenleme ve silme işlemleri bu listeden yapılabilir.

## ⚙️ Kullanılan Teknolojiler

- **Spring Boot** – Modern ve kurumsal Java web uygulamaları için güçlü framework.
- **Spring Data JPA** – Veritabanı işlemleri için sadeleştirilmiş ORM çözümü.
- **PostgreSQL** – Güçlü ve açık kaynaklı veritabanı yönetim sistemi.
- **Thymeleaf** – Sunucu taraflı şablon motoru ile dinamik HTML sayfaları oluşturma.
- **Spring MVC** – Model-View-Controller mimarisi ile yapılandırılmış web uygulaması geliştirme.
- **Bootstrap** – Kullanıcı arayüzü tasarımı için responsive ve modern CSS framework.
- **Lombok** – Java sınıflarında boilerplate kodları azaltmak için kullanılan kütüphane.

## 💡 Neden Bu Proje?

Bu projeyi, **hiç bilmediğim bir teknoloji olan Spring Boot ile**, tamamen sıfırdan öğrenerek geliştirdim. Daha önce farklı dillerde edindiğim yazılım tecrübemi bu yeni dünyaya taşıyarak kısa sürede fonksiyonel ve gerçek bir uygulama ortaya koymayı başardım.

- 🧠 Yeni bir framework öğrenme kabiliyeti.
- 🧰 Backend yapıların kurulumunu ve REST API geliştirme becerisi.
- 🔍 Veritabanı ilişkilerini yönetme ve sorgulama deneyimi.
- 🚀 Kurumsal projelere yakın mimaride uygulama geliştirme tecrübesi.

## 🚀 Projeyi Çalıştırmak

1. **Projeyi Klonlayın:**

   ```bash
   git clone https://github.com/furkan-karapinar/Warranty_Project.git
   cd Warranty_Project
   ```

2. **PostgreSQL Veritabanını Kurun:**

   - PostgreSQL'i sisteminize kurun.
   - warranty_db adında bir veritabanı oluşturun.
   - Veritabanı kullanıcı adı ve şifresini belirleyin.

3. **Veritabanı Bağlantı Ayarlarını Yapılandırın:**

   - src/main/resources/application.properties dosyasını açın ve aşağıdaki gibi düzenleyin:


   ```bash
   spring.datasource.url=jdbc:postgresql://localhost:5432/warranty_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   ```

4. **Uygulamayı Başlatın:**
   
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Uygulamayı Tarayıcıda Açın:**

   ```bash
   http://localhost:8080
   ```
   
