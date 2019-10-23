public class Plan1571770263706 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}

}

}

for (int i = 0; i < 4 ; i++) {
DecreaseTraffic("A");
}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
}



}
}
