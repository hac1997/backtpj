export interface Author {
  userId: number;
  name: string;
  email: string;
}

export interface Post {
  postId: number;
  title: string;
  body: string;
  author: Author;
  createdAt: string;
}

export interface PostRequest {
  title: string;
  body: string;
  userId: number;
}
