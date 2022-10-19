package com.kor.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("===프로그램 시작===");

		Scanner sc = new Scanner(System.in);

		List<Article> articles = new ArrayList<>();

		while (true) {
			System.out.printf("명령어)");

			String cmd = sc.nextLine();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			} else if (cmd.equals("exit")) {
				break;
			}

			else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다");
				} else {
					for (Article article : articles) {

						System.out.println("번호 : " + article.id);
						System.out.println("제목 : " + article.title);
						System.out.println("제목 : " + article.body);
					}
				}
			}

			else if (cmd.equals("article write")) {
				int id = articles.size();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				System.out.println("제목 : " + title);
				System.out.println("내용 : " + body);
				id++;

				Article article = new Article(id, title, body);
				articles.add(article);
			}

			else {
				System.out.println("존재하지 않는 명령어입니다");
				continue;
			}
		}

		sc.close();

		System.out.println("===프로그램 끝===");
	}
}

class Article {
	int id;
	String title;
	String body;
	
	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
}