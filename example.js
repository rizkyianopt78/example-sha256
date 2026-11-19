const crypto = require("crypto");

const clientId = "YOUR_CLIENT_ID";
const secret = "YOUR_CLIENT_SECRET";

// Generate 8 random bytes
const externalId = crypto.randomBytes(8).toString("hex");

// Generate RFC3339 timestamp
const timestamp = new Date().toISOString();

// HMAC-SHA256
const signingString = `${clientId}|${externalId}|${timestamp}`;

const signature = crypto
    .createHmac("sha256", secret)
    .update(signingString, "utf8")
    .digest("hex");

console.log("External ID:", externalId);
console.log("Timestamp:", timestamp);
console.log("Signature:", signature);