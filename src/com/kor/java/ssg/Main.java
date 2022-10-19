package com.kor.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("===프로그램 시작===");

		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0;

		List<Article> articles = new ArrayList<>();

		while (true) {
			System.out.printf("명령어)");

			String command = sc.nextLine();
			
			command = command.trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			} else if (command.equals("system exit")) {
				break;
			}

			else if (command.equals("article list")) {
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

			else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				System.out.println("제목 : " + title);
				System.out.println("내용 : " + body);

				Article article = new Article(id, title, body);
				articles.add(article);
				
				System.out.println(id + "번 글이 생성되었습니다");
			}

			else {
				System.out.printf("%s(은)는 존재하지 않는 명령어입니다\n",command);
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