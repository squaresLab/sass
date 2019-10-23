public class Plan1571773697821 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
}

StartServer("A");
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}





}
}
