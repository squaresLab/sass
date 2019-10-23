public class Plan1571768384359 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
StartServer("C");

}

StartServer("B");

}

for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

StartServer("B");



}
}
