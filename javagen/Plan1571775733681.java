public class Plan1571775733681 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
StartServer("A");
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("C");
}

}

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



StartServer("A");


}
}
