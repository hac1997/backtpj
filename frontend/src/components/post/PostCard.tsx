'use client';
import { Post } from "@/types/post";

interface PostCardProps {
  post: Post;
  onSelect?: (id: number) => void;
}

export default function PostCard({ post, onSelect }: PostCardProps) {
  return (
    <div
      onClick={() => onSelect && onSelect(post.id)}
      className="bg-white rounded-2xl shadow p-5 mb-4 cursor-pointer hover:shadow-lg transition"
    >
      <h2 className="text-xl font-semibold mb-2">{post.title}</h2>
      <p className="text-gray-700 mb-3">{post.body.slice(0, 150)}...</p>
      <div className="flex justify-between text-sm text-gray-500">
        <div className="flex gap-2">
          {post.tags.map((tag, i) => (
            <span
              key={i}
              className="bg-gray-200 text-gray-800 px-2 py-1 rounded-lg"
            >
              #{tag}
            </span>
          ))}
        </div>
        <span>{new Date(post.createdAt).toLocaleDateString()}</span>
      </div>
    </div>
  );
}
