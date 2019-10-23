public class Plan1571770170156 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}

for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

StartServer("B");



for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("A");

}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}




}
}
