import { api } from "./api";
import { Post } from "@/types/post";

export const PostService = {
  getAll: async (): Promise<Post[]> => {
    const response = await api.get("/posts");
    return response.data;
  },
  getById: async (id: number): Promise<Post> => {
    const response = await api.get(`/posts/${id}`);
    return response.data;
  },
  create: async (data: Partial<Post>) => {
    const response = await api.post("/posts", data);
    return response.data;
  },
};
