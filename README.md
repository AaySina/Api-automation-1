#API Automation Framework Day 32 – Dibimbing Bootcamp

## 📌 Deskripsi Proyek
Proyek ini adalah kerangka kerja otomatisasi pengujian *end-to-end* untuk **API Sport Reservation Bootcamp**.

Dibangun dengan pendekatan *clean code* dan siap CI/CD, kerangka kerja ini bertujuan untuk menyediakan sistem pengujian API yang **andal, mudah dikelola, dan skalabel**.

---

## 🛠️ Tumpukan Teknologi
| Komponen | Teknologi | Tujuan |
| :--- | :--- | :--- |
| Bahasa | **Java 21** | Bahasa inti implementasi |
| Build Tool | **Gradle** | Pengelolaan dependensi dan *build* |
| Testing Framework | **TestNG** | Menjalankan dan mengelompokkan skenario pengujian |
| HTTP Client | **Rest Assured** | Interaksi dengan API (Request/Response) |
| Reporting | **ExtentReports & Allure** | Visualisasi dan pelaporan hasil eksekusi |
| CI/CD | **GitHub Actions** | Otomasi eksekusi pengujian pada *pipeline* |

---

## 🚀 Fitur Utama
* **Desain Modular:** Menggunakan pola `BaseTest` dan *helper classes* untuk kode yang dapat digunakan kembali.
* **Data-Driven Testing:** Memanfaatkan file **JSON** sebagai sumber data untuk *request body* dan data dinamis (misalnya, token, ID).
* **CI/CD Pipeline:** Otomasi eksekusi pengujian pada setiap *commit* ke *branch* `master`.
* **Laporan Otomatis:** Laporan Allure dan ExtentReport di-generate dan diunggah sebagai *artifact* CI/CD.
* **Konfigurasi Fleksibel:** Pengaturan *environment* (baseUrl, timeout, credential) melalui `config.properties`.

---

---

## ⚙️ Cara Menjalankan

### 1. Eksekusi Lokal
1.  **Clone Repositori:**
    ```bash
    git clone [https://github.com/username/API-AUTOMATION-Dibimbing.git](https://github.com/username/API-AUTOMATION-Dibimbing.git)
    ```
2.  **Jalankan Pengujian:**
    ```bash
    ./gradlew clean test
    ```
3.  **Lihat Laporan:**
    * **ExtentReport:** Buka `reports-output/index.html`
    * **Allure Report:** Laporan dapat di-generate dari folder `build/reports/allure/` (memerlukan instalasi Allure CLI).

### 2. CI/CD (GitHub Actions)
* *Workflow* akan berjalan secara otomatis pada setiap *push* atau *pull request* ke *branch* `master`.
* Hasil eksekusi dan artefak laporan (Allure & ExtentReport) dapat diunduh dari tab **Actions** pada halaman repositori.

---

## 📊 Bukti dan Dokumentasi Tugas

| Dokumentasi | Keterangan | Tautan |
| :--- | :--- | :--- |
| **Test Plan & Cases** | Detail perencanaan dan skenario pengujian fungsionalitas API. | **[https://docs.google.com/spreadsheets/d/1rAIknbR1oud4TP6sbQ7zcd2y9I17VelIvwaCFfQBuLo/edit?hl=id&gid=0#gid=0]** |
| **Jawaban Assignment** | Jawaban untuk pertanyaan tertulis/Q&A tugas bootcamp. | **[https://docs.google.com/document/d/1iLe5A_abDvWOcWp6MX2qR4btXogzsx5FiD2OSa5A3Xs/edit?tab=t.0]** |
| **Status CI/CD** | Status eksekusi otomatis terakhir di GitHub Actions. | [![CI Status](https://github.com/username/API-AUTOMATION-Dibimbing/actions/workflows/ci.yml/badge.svg)](https://github.com/username/API-AUTOMATION-Dibimbing/actions/workflows/ci.yml) |

---
