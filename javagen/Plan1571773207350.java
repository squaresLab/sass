public class Plan1571773207350 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

} else {
StartServer("C");
}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}

StartServer("C");


for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

StartServer("C");




}
}
