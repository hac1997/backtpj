'use client';
import { useEffect, useState } from "react";
import { PostService } from "@/services/postService";
import PostCard from "./PostCard";
import { Post } from "@/types/post";

export default function PostList() {
  const [posts, setPosts] = useState<Post[]>([]);

  useEffect(() => {
    PostService.getAll().then(setPosts);
  }, []);

  return (
    <div className="grid gap-4">
      {posts.map((p) => (
        <PostCard key={p.id} post={p} />
      ))}
    </div>
  );
}
