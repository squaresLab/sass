public class Plan1571774718968 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
DecreaseTraffic("A");

}


for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

StartServer("C");
StartServer("B");



}
}
