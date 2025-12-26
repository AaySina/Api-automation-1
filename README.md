# API Automation Framework: Sport Reservation Bootcamp

## Latar Belakang Proyek
Proyek ini merupakan **kerangka kerja otomatisasi pengujian *end-to-end*** untuk **API Sport Reservation Bootcamp** yang diselenggarakan oleh Dibimbing (Day 32).

Dibangun dengan filosofi *clean code* dan dirancang siap untuk integrasi CI/CD, kerangka kerja ini bertujuan untuk menyediakan sistem pengujian API yang **andal, mudah dikelola, dan skalabel**.

---

## Tumpukan Teknologi (Tech Stack)

| Kategori | Teknologi | Tujuan Utama |
| :--- | :--- | :--- |
| **Bahasa Pemrograman** | Java 21 | Bahasa inti implementasi untuk logika pengujian. |
| **Build Tool** | Gradle | Pengelolaan dependensi, tugas *build*, dan eksekusi. |
| **Testing Framework** | TestNG | Menjalankan, mengelompokkan, dan mengatur prioritas skenario pengujian. |
| **HTTP Client** | Rest Assured | Interaksi yang mudah dengan API (mengirim *request* dan memvalidasi *response*). |
| **Reporting** | ExtentReports & Allure | Visualisasi hasil eksekusi pengujian yang detail dan informatif. |
| **CI/CD** | GitHub Actions | Otomasi eksekusi pengujian pada *pipeline* terintegrasi. |

---

## Fitur Utama Kerangka Kerja

* **Desain Modular dan Reusable:** Implementasi pola `BaseTest` dan *helper classes* (Page Object Model/Service Layer) untuk kode yang efisien dan dapat digunakan kembali.
* **Data-Driven Testing (DDT):** Menggunakan file **JSON** sebagai sumber data eksternal untuk *request body* dinamis dan data kredensial (token, ID).
* **Pipeline CI/CD Otomatis:** Otomasi penuh pengujian pada setiap *commit* ke *branch* utama (`master`).
* **Laporan Komprehensif:** Laporan **Allure** dan **ExtentReport** di-*generate* dan diunggah sebagai **artefak CI/CD**.
* **Konfigurasi Fleksibel:** Pengaturan *environment* (baseUrl, timeout, credential) dikelola secara terpusat melalui file `config.properties`.

---

## Cara Menjalankan

### 1. Eksekusi Lokal

1.  **Clone Repositori:**
    ```bash
    git clone [https://github.com/username/API-AUTOMATION-Dibimbing.git](https://github.com/username/API-AUTOMATION-Dibimbing.git)
    ```
2.  **Jalankan Pengujian (Menggunakan Gradle):**
    ```bash
    # Menjalankan semua skenario pengujian
    ./gradlew clean test
    ```
3.  **Akses Laporan:**
    * **ExtentReport:** Buka `reports-output/index.html` di *browser*.
    * **Allure Report:** Laporan dapat di-*generate* secara lokal (memerlukan instalasi Allure CLI) dari folder `build/reports/allure/`.

### 2. Eksekusi CI/CD (GitHub Actions)

* *Workflow* CI/CD akan terpicu secara otomatis pada setiap *push* atau *pull request* yang ditujukan ke *branch* `master`.
* Hasil eksekusi dan artefak laporan (Allure & ExtentReport) dapat diunduh dari tab **Actions** pada halaman repositori GitHub.

---

## Dokumentasi dan Bukti Tugas

| Dokumen | Keterangan | Tautan |
| :--- | :--- | :--- |
| **Test Plan & Cases** | Detail perencanaan dan skenario pengujian fungsionalitas API. | [Tautan Spreadsheet](https://docs.google.com/spreadsheets/d/1rAIknbR1oud4TP6sbQ7zcd2y9I17VelIvwaCFfQBuLo/edit?hl=id&gid=0#gid=0) |
| **Jawaban Assignment** | Jawaban untuk pertanyaan tertulis/Q&A tugas bootcamp. | [Tautan Dokumen](https://docs.google.com/document/d/1iLe5A_abDvWOcWp6MX2qR4btXogzsx5FiD2OSa5A3Xs/edit?tab=t.0) |
| **Status CI/CD** | Status eksekusi otomatis terakhir di GitHub Actions. | [![CI Status](https://github.com/AaySina/Api-automation-1/workflows/CI/badge.svg)](https://github.com/AaySina/Api-automation-1/actions) |
