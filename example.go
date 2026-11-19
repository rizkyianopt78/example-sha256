package main

import (
	"crypto/hmac"
	"crypto/rand"
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"time"
)

func main() {
	clientId := "YOUR_CLIENT_ID"
	secret := "YOUR_CLIENT_SECRET"
	b := make([]byte, 8)
	_, _ = rand.Read(b)
	externalId := hex.EncodeToString(b)
	timestamp := time.Now().Format(time.RFC3339)

	// ── HMAC-SHA256 (Merchant / Customer inquiry POST) ──────────────────────
	hmacSigningString := fmt.Sprintf("%s|%s|%s", clientId, externalId, timestamp)
	mac := hmac.New(sha256.New, []byte(secret))
	mac.Write([]byte(hmacSigningString))
	hmacSignature := hex.EncodeToString(mac.Sum(nil))
	fmt.Println(hmacSignature)
}
