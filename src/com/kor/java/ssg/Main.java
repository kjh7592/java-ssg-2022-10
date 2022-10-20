package com.kor.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static List<Article> articles = new ArrayList<>();
	
	public static void main(String[] args) {
		System.out.println("===프로그램 시작===");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;
		
		
		while(true) {
			System.out.printf("명령어)");
			String command = sc.nextLine();
			
			command = command.trim();
			
			if(command.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			if(command.equals("system exit")) {
				break;
			}
			else if(command.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				String regDate = Util.getNowDateStr();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				Article article = new Article(id, regDate, title, body);
				articles.add(article);
				
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
			}
			else if(command.equals("article list")) {
				if(articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
					continue;
				}
				
				System.out.println("번호	|	제목");
				for(int i = articles.size() - 1; i >= 0 ; i--) {
					Article article = articles.get(i);
					
					System.out.printf("%d	|	%s\n", article.id, article.title);
				}
			}
			
			else if(command.startsWith("article detail ")) {
				
				String commandBits[] = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				
				Article foundArticle = getArticleById(id);
				
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				System.out.println("번호 : " + foundArticle.id);
				System.out.println("날짜 : " + foundArticle.regDate);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);
			}
			else if(command.startsWith("article modify ")) {
				
				String commandBits[] = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				
				Article foundArticle = getArticleById(id);
				
				
				if(foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine();
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine();
				
				foundArticle.title = title;
				foundArticle.body = body;
				System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
			}
			
			else if(command.startsWith("article delete ")) {
				
				String commandBits[] = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				
				int foundindex = getArticleIndexById(id);
				
				if(foundindex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				} 
				articles.remove(foundindex);
				System.out.printf("%d 게시물이 삭제되었습니다.\n", id);
			}
			
			else {
				System.out.printf("%s(은)는 존재하지 않는 명령어입니다.\n", command);
			}
		}
		
		

		sc.close();

		System.out.println("===프로그램 끝===");
	}

	private static Article getArticleById(int id) {
		
		int index = getArticleIndexById(id);
		
		if(index != -1) {
			
			return articles.get(index);
		}
		return null;
	}

	private static int getArticleIndexById(int id) {
		
		for(int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			
			if(id == article.id) {
				return i;
			}
		}
		return -1;
	}
}

class Article {
	int id;
	String regDate;
	String title;
	String body;
	
	public Article(int id, String regDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
	}
}