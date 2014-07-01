# Optimistic Locking dengan Hibernate #

### Referensi ###

* http://docs.jboss.org/hibernate/orm/4.3/manual/en-US/html/ch05.html#d5e2835
* http://docs.jboss.org/hibernate/orm/4.3/manual/en-US/html/ch13.html#transactions-optimistic
* http://www.javacodegeeks.com/2012/11/jpahibernate-version-based-optimistic-concurrency-control.html

### Cara Menjalankan ###

1. Buka dua command prompt untuk mensimulasikan dua user mengupdate data customer secara berbarengan
2. Masuk ke folder project
3. Di command prompt pertama, update nama user menjadi Lukman. Tentukan delay 10 detik agar bisa didahului oleh command prompt kedua

        mvn exec:java -Dexec.mainClass=com.muhardin.endy.belajar.hibernate.App \
        -Dexec.args="10 Lukman"

4. Di command prompt kedua, update nama user menjadi Faris. Tentukan delay 2 detik agar bisa mendahului command prompt pertama

        mvn exec:java -Dexec.mainClass=com.muhardin.endy.belajar.hibernate.App \
        -Dexec.args="2 Faris"

5. Seharusnya command prompt kedua akan sukses, dan command prompt pertama akan gagal karena telah didahului user lain.


