public class Plan1571768304332 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
for (int i = 0; i < 5 ; i++) {
StartServer("A");
}

if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
StartServer("A");
}



for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



}
}
