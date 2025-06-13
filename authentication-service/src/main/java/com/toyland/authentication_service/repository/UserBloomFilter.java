//package com.toyland.authentication_service.repository;
//
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.stereotype.Component;
//
//import java.util.BitSet;
//
//public class UserBloomFilter {
//    private BitSet bitset;
//    private int bitsetSize;
//    private int hashCount;
//
//    public UserBloomFilter(int bitsetSize, int hashCount) {
//        this.bitsetSize = bitsetSize;
//        this.hashCount = hashCount;
//        this.bitset = new BitSet(bitsetSize);
//    }
//
//    // Thêm userID vào Bloom filter
//    public void addUser(String userId) {
//        int[] hashes = getHashes(userId);
//        for (int hash : hashes) {
//            bitset.set(Math.abs(hash % bitsetSize), true);
//        }
//    }
//
//    // Kiểm tra userID có thể đã tồn tại không
//    public boolean mightContainUser(String userId) {
//        int[] hashes = getHashes(userId);
//        for (int hash : hashes) {
//            if (!bitset.get(Math.abs(hash % bitsetSize))) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    // Hàm băm cơ bản
//    private int[] getHashes(String userId) {
//        int[] hashes = new int[hashCount];
//        int hash1 = userId.hashCode();
//        int hash2 = hash1 >>> 16;
//        for (int i = 0; i < hashCount; i++) {
//            int combinedHash = hash1 + i * hash2 + i * i;
//            hashes[i] = combinedHash;
//        }
//        return hashes;
//    }
//}
